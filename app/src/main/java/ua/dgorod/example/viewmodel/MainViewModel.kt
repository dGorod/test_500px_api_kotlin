package ua.dgorod.example.viewmodel

import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import ua.dgorod.example.domain.interactor.PhotosInteractor
import ua.dgorod.example.mapper.PhotoUiMapper
import ua.dgorod.example.model.PhotoUiModel

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
open class MainViewModel(private val photosSource: PhotosInteractor): RxViewModel() {

    private val photoMapper = PhotoUiMapper()

    val loadingStatus: MutableLiveData<Boolean> = MutableLiveData()
    val photosList: MutableLiveData<Parcel<List<PhotoUiModel>>> = MutableLiveData()

    fun getPhotos(page: Int, firstLoad: Boolean = false) {
        if (firstLoad && photosList.value?.content?.isNotEmpty() == true) {
            // no need to load data again
            return
        }

        disposable.add(photosSource.get(page)
                .filter { it.isNotEmpty() }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingStatus.value = true }
                .doOnNext { loadingStatus.value = false }
                .doOnError { Timber.e(it) }
                .subscribe(
                        { photosList.value = Parcel.success(photoMapper.map(it)) },
                        { photosList.value = Parcel.error(it) }
                )
        )
    }
}