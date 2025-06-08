package com.london.weather.data.repositories

import com.london.weather.data.datasources.WeatherDataSource
import com.london.weather.data.dtos.WeatherForecastDto
import com.london.weather.domain.repositories.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherDataSource: WeatherDataSource
): WeatherRepository {
    override suspend fun getWeatherForecast(long: Double, lat: Double): WeatherForecastDto {
        return weatherDataSource.getWeatherForecast(long, lat)
    }
}