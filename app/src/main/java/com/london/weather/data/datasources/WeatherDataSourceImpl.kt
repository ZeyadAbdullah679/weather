package com.london.weather.data.datasources

import com.london.weather.data.dtos.WeatherForecastDto
import com.london.weather.common.DataState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val BASE_URL = "https://api.open-meteo.com/v1/forecast"

class WeatherDataSourceImpl(
    private val httpClient: HttpClient
) : WeatherDataSource {
    override suspend fun getWeatherForecast(
        long: Double,
        lat: Double
    ): Flow<DataState<WeatherForecastDto>> {
        return flow {
            emit(DataState.Loading)
            try {
                val response = httpClient.get(BASE_URL) {
                    parameter("latitude", lat)
                    parameter("longitude", long)
                    parameter(
                        "current",
                        "temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation_probability,wind_speed_10m,weather_code,uv_index,surface_pressure,cloud_cover"
                    )
                    parameter("hourly", "temperature_2m,weather_code")
                    parameter("daily", "weather_code,temperature_2m_max,temperature_2m_min")
                    parameter("timezone", "auto")
                }
                if (response.status == HttpStatusCode.OK) {
                    emit(DataState.Success(response.body<WeatherForecastDto>()))
                } else {
                    emit(DataState.Error("Error: ${response.status.value} - ${response.status.description}"))
                }
            } catch (e: Exception) {
                emit(DataState.Error(e.message ?: "Unknown error"))
            }
        }
    }
}