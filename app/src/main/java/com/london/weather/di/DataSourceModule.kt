package com.london.weather.di

import com.london.weather.data.datasources.WeatherDataSource
import com.london.weather.data.datasources.WeatherDataSourceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import org.koin.dsl.module

val dataSourceModule = module {
    single { HttpClient(Android) }
    single <WeatherDataSource> { WeatherDataSourceImpl(get()) }
}