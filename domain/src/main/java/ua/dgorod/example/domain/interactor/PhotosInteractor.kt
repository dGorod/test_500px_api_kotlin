package ua.dgorod.example.domain.interactor

import io.reactivex.Flowable
import ua.dgorod.example.domain.model.Photo
import ua.dgorod.example.domain.repository.PhotosRepository

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
class PhotosInteractor(private val photosRepo: PhotosRepository) {

    fun get(page: Int): Flowable<List<Photo>> = photosRepo.getAll(page)
}