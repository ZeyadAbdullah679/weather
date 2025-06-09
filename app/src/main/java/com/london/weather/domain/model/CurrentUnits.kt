package com.london.weather.domain.model

data class CurrentUnits(
    val apparentTemperature: String,
    val precipitationProbability: String,
    val relativeHumidity2m: String,
    val surfacePressure: String,
    val temperature2m: String,
    val windSpeed10m: String,
    val cloudCover: String
)