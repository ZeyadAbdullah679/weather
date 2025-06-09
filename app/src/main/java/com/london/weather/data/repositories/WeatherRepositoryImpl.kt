package com.london.weather.data.repositories

import com.london.weather.data.datasources.WeatherDataSource
import com.london.weather.domain.mapper.toWeatherForecast
import com.london.weather.domain.model.WeatherForecast
import com.london.weather.domain.repositories.WeatherRepository
import com.london.weather.common.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WeatherRepositoryImpl(
    private val weatherDataSource: WeatherDataSource
) : WeatherRepository {
    override suspend fun getWeatherForecast(
        long: Double,
        lat: Double
    ): Flow<DataState<WeatherForecast>> {
        return weatherDataSource.getWeatherForecast(long, lat).map { dataState ->
            when (dataState) {
                is DataState.Loading -> DataState.Loading
                is DataState.Success -> DataState.Success(dataState.data.toWeatherForecast())
                is DataState.Error -> DataState.Error(dataState.message)
            }
        }
    }
}