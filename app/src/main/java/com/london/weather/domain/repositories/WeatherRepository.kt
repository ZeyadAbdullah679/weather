package com.london.weather.domain.repositories

import com.london.weather.domain.model.WeatherForecast
import com.london.weather.common.DataState
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherForecast(long: Double, lat: Double): Flow<DataState<WeatherForecast>>
}