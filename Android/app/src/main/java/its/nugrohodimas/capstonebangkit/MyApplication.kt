package its.nugrohodimas.capstonebangkit

import android.app.Application
import its.nugrohodimas.capstonebangkit.di.useCaseModule
import its.nugrohodimas.capstonebangkit.di.viewModelModule
import its.nugrohodimas.core.di.databaseModule
import its.nugrohodimas.core.di.networkModule
import its.nugrohodimas.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}