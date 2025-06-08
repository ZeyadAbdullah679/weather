package com.london.weather.domain.model

data class Hourly(
    val temperature2m: List<Double>,
    val time: List<String>,
    val weatherCode: List<Int>
)