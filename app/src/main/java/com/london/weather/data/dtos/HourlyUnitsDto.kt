package com.london.weather.data.dtos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyUnitsDto(
    @SerialName("temperature_2m")
    val temperature2m: String?,
    @SerialName("time")
    val time: String?,
    @SerialName("weather_code")
    val weatherCode: String?
)