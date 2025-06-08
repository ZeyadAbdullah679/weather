package com.london.weather.data.repositories

import com.london.weather.data.datasources.WeatherDataSource
import com.london.weather.domain.mapper.toWeatherForecast
import com.london.weather.domain.model.WeatherForecast
import com.london.weather.domain.repositories.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherDataSource: WeatherDataSource
): WeatherRepository {
    override suspend fun getWeatherForecast(long: Double, lat: Double): WeatherForecast {
        return weatherDataSource.getWeatherForecast(long, lat).toWeatherForecast()
    }
}