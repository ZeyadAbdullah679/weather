package com.london.weather.domain.model

import com.london.weather.domain.model.Current
import com.london.weather.domain.model.CurrentUnits
import com.london.weather.domain.model.Daily
import com.london.weather.domain.model.DailyUnits
import com.london.weather.domain.model.Hourly
import com.london.weather.domain.model.HourlyUnits

data class WeatherForecast(
    val current: Current,
    val currentUnits: CurrentUnits,
    val daily: Daily,
    val dailyUnits: DailyUnits,
    val hourly: Hourly,
    val hourlyUnits: HourlyUnits,
)