package ua.dgorod.example.data.di

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import ua.dgorod.example.data.BuildConfig
import ua.dgorod.example.data.R
import ua.dgorod.example.data.api.ApiInterface
import java.util.concurrent.TimeUnit

/**
 * Singleton bean. Provides API client.
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
val apiModule: Module = applicationContext {

    fun getHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) Level.BASIC else Level.NONE

        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(2, TimeUnit.MINUTES)
                .build()
    }

    fun getWebService(context: Context, httpClient: OkHttpClient): ApiInterface {
        return Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
                .create(ApiInterface::class.java)
    }

    bean { getHttpClient() }

    bean { getWebService(androidApplication(), get()) }
}

