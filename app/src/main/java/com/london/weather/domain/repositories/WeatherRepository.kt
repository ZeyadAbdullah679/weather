package com.london.weather.domain.repositories

interface WeatherRepository {
    suspend fun getWeatherForecast(long: Double, lat: Double): WeatherForecastDto
}