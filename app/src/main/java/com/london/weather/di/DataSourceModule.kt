package com.london.weather.di

import com.london.weather.data.datasources.WeatherDataSource
import com.london.weather.data.datasources.WeatherDataSourceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataSourceModule = module {
    single { HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // Ignore unknown fields in the JSON response
            })
        }
    } }
    single <WeatherDataSource> { WeatherDataSourceImpl(get()) }
}