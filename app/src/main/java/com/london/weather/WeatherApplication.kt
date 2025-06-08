package com.london.weather

import android.app.Application
import com.london.weather.di.dataSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class WeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@WeatherApplication)
            //Load Modules
            modules(interactionModule, dataSourceModule, presentationModule, repositoryModule)
        }
    }
}