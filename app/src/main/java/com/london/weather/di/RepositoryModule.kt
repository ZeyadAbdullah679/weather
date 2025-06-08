package com.london.weather.di

import com.london.weather.data.repositories.WeatherRepositoryImpl
import com.london.weather.domain.repositories.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}