package com.london.weather.data.datasources

import com.london.weather.data.dtos.WeatherForecastDto

interface WeatherDataSource {
    suspend fun getWeatherForecast(long: Double, lat: Double): WeatherForecastDto
}