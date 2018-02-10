package ua.dgorod.example.data.repository

import android.content.Context
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import ua.dgorod.example.data.R
import ua.dgorod.example.data.PhotoSizes
import ua.dgorod.example.data.api.ApiInterface
import ua.dgorod.example.data.api.dto.PhotoDto
import ua.dgorod.example.data.api.dto.PopularPhotosDto
import ua.dgorod.example.data.db.MyDatabase
import ua.dgorod.example.data.mapper.ImageEntityMapper
import ua.dgorod.example.data.mapper.PhotoEntityMapper
import ua.dgorod.example.data.mapper.PhotoDtoMapper
import ua.dgorod.example.data.mapper.PhotoWithLinksMapper
import ua.dgorod.example.domain.Const
import ua.dgorod.example.domain.model.Photo
import ua.dgorod.example.domain.repository.PhotosRepository
import java.net.UnknownHostException

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
class PhotosRepositoryImpl(context: Context,
                           private val api: ApiInterface,
                           private val db: MyDatabase
) : PhotosRepository {

    private val url = context.getString(R.string.api_request_popular_photos)
    private val key = context.getString(R.string.api_500px_consumer_key)
    private val sizes = "${PhotoSizes.SMALL},${PhotoSizes.BIG}"

    private val photoDtoMapper = PhotoDtoMapper()
    private val photoWithLinksMapper = PhotoWithLinksMapper()
    private val photoEntityMapper = PhotoEntityMapper()
    private val imageEntityMapper = ImageEntityMapper()

    override fun getAll(page: Int): Flowable<List<Photo>> {
        val limit = Const.DEFAULT_PAGE_SIZE

        val localCall = db.photos().getAll(limit, page * limit)
                .map { photoWithLinksMapper.map(it) }

        val networkCall = api.getPhotos(url, key, sizes, page + 1)
                .toFlowable()
                .onErrorReturn { if (it is UnknownHostException) PopularPhotosDto() else throw it }
                .doOnNext { saveLocally(it.photos) }
                .map { photoDtoMapper.map(it.photos) }

        return Flowable.concat(networkCall, localCall).subscribeOn(Schedulers.io())
    }

    override fun get(id: Long): Maybe<Photo> = db.photos().get(id).map { photoWithLinksMapper.map(it) }

    private fun saveLocally(data: List<PhotoDto>) {
        if (data.isEmpty()) return

        db.runInTransaction {
            data.forEach { photo ->
                db.photos().insert(photoEntityMapper.map(photo))

                photo.images.forEach { image ->
                    db.images().insert(imageEntityMapper.map(image).also { it.photoId = photo.id })
                }
            }
        }
    }
}