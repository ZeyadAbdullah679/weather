package com.london.weather.domain.repositories

import com.london.weather.domain.model.WeatherForecast

interface WeatherRepository {
    suspend fun getWeatherForecast(long: Double, lat: Double): WeatherForecast
}