package ua.dgorod.example

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.squareup.picasso.Picasso
import org.koin.Koin
import org.koin.android.ext.android.startKoin
import org.koin.android.logger.AndroidLogger
import timber.log.Timber
import ua.dgorod.example.data.di.apiModule
import ua.dgorod.example.data.di.dbModule
import ua.dgorod.example.di.photosModule

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 10-Dec-17.
 */
class App: Application() {

    companion object {
        val appModules = listOf(apiModule, dbModule, photosModule)
    }

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        //BlockCanary.install(this, AppBlockCanaryContext()).start()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Koin.logger = AndroidLogger()

            val built = Picasso.Builder(this).indicatorsEnabled(true).build()
            Picasso.setSingletonInstance(built)
        }

        startKoin(this, appModules)
    }
}