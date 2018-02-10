package ua.dgorod.example.data.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import ua.dgorod.example.data.db.MyDatabase

/**
 * Singleton bean. Provides local database.
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
val dbModule: Module = applicationContext {

    bean { MyDatabase.getInstance(androidApplication()) }
}