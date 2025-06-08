package com.london.weather.data.dtos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastDto(
    @SerialName("current")
    val currentDto: CurrentDto?,
    @SerialName("current_units")
    val currentUnitsDto: CurrentUnitsDto?,
    @SerialName("daily")
    val dailyDto: DailyDto?,
    @SerialName("daily_units")
    val dailyUnitsDto: DailyUnitsDto?,
    @SerialName("elevation")
    val elevation: Int?,
    @SerialName("generationtime_ms")
    val generationtimeMs: Double?,
    @SerialName("hourly")
    val hourlyDto: HourlyDto?,
    @SerialName("hourly_units")
    val hourlyUnitsDto: HourlyUnitsDto?,
    @SerialName("latitude")
    val latitude: Double?,
    @SerialName("longitude")
    val longitude: Double?,
    @SerialName("timezone")
    val timezone: String?,
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String?,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int?
)