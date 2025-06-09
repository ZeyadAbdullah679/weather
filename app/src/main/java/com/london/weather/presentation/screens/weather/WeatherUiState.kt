package com.london.weather.presentation.screens.weather

import com.london.weather.domain.model.WeatherForecast

sealed interface WeatherUiState {
    data class Success(
        val weatherForecast: WeatherForecast,
        val longitude: Double,
        val latitude: Double,
        val cityName: String
    ) : WeatherUiState

    data class Error(val message: String) : WeatherUiState
    data object Loading : WeatherUiState
}