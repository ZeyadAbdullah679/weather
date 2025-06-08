package com.london.weather.domain.usecases

import com.london.weather.domain.model.WeatherForecast
import com.london.weather.domain.repositories.WeatherRepository

class GetWeatherForecastUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(long: Double, lat: Double): WeatherForecast {
        return repository.getWeatherForecast(long, lat)
    }
}