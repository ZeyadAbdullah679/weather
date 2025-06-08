package com.london.weather.domain.model

data class WeatherForecast(
    val current: Current,
    val currentUnits: CurrentUnits,
    val daily: Daily,
    val dailyUnits: DailyUnits,
    val hourly: Hourly,
    val hourlyUnits: HourlyUnits,
)