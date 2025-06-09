package com.london.weather.domain.usecases

import com.london.weather.common.DataState
import com.london.weather.domain.model.WeatherForecast
import com.london.weather.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetWeatherForecastUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(long: Double, lat: Double): Flow<DataState<WeatherForecast>> {
        return repository.getWeatherForecast(long, lat)
    }
}