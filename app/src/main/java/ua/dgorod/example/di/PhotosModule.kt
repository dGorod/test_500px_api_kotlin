package ua.dgorod.example.di

import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import ua.dgorod.example.data.repository.PhotosRepositoryImpl
import ua.dgorod.example.domain.interactor.PhotosInteractor
import ua.dgorod.example.domain.repository.PhotosRepository
import ua.dgorod.example.ui.activity.MainActivity
import ua.dgorod.example.viewmodel.MainViewModel

/**
 * Bean with [MainActivity] context scope. Provides beans for work with photos.
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
val photosModule: Module = applicationContext {

    context(MainActivity::class.java.simpleName) {
        viewModel { MainViewModel(get()) }
    }

    bean { PhotosInteractor(get()) }

    bean { PhotosRepositoryImpl(androidApplication(), get(), get()) as PhotosRepository }
}