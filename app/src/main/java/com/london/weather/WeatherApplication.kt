package com.london.weather

import android.app.Application
import com.london.weather.di.dataSourceModule
import com.london.weather.di.repositoryModule
import com.london.weather.di.useCaseModule
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
            modules(dataSourceModule, repositoryModule, useCaseModule)
        }
    }
}