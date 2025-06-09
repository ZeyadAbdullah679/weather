package com.london.weather.presentation.screens.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.london.weather.common.DataState
import com.london.weather.domain.model.WeatherForecast
import com.london.weather.domain.usecases.GetWeatherForecastUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase
) : ViewModel() {

    private val _weatherState: MutableStateFlow<DataState<WeatherForecast>> =
        MutableStateFlow(DataState.Loading)
    val weatherState: StateFlow<DataState<WeatherForecast>> = _weatherState.asStateFlow()

    private val _weatherUiState: MutableStateFlow<WeatherUiState> =
        MutableStateFlow(WeatherUiState.Loading)
    val weatherUiState: StateFlow<WeatherUiState> = _weatherUiState.asStateFlow()

    fun getWeatherInfo(lat: Double, long: Double, cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getWeatherForecastUseCase(lat, long).collect { dataState ->
                _weatherState.update { dataState }
                when (dataState) {
                    is DataState.Success -> {
                        updateUiState(WeatherUiState.Success(dataState.data, long, lat, cityName))
                    }

                    is DataState.Error -> {
                        updateUiState(WeatherUiState.Error(dataState.message))
                    }

                    DataState.Loading -> {
                        updateUiState(WeatherUiState.Loading)
                    }
                }
            }
        }
    }

    fun updateUiState(state: WeatherUiState) {
        _weatherUiState.update { state }
    }
}