package com.london.weather.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.london.weather.domain.usecases.GetWeatherForecastUseCase

val useCaseModule = module {
    singleOf(::GetWeatherForecastUseCase)
}