package com.london.weather.data.datasources

import com.london.weather.data.dtos.WeatherForecastDto
import com.london.weather.common.DataState
import kotlinx.coroutines.flow.Flow

interface WeatherDataSource {
    suspend fun getWeatherForecast(long: Double, lat: Double): Flow<DataState<WeatherForecastDto>>
}