package com.london.weather.domain.mapper

import com.london.weather.data.dtos.*
import com.london.weather.domain.model.*

fun WeatherForecastDto.toWeatherForecast(): WeatherForecast {
    return WeatherForecast(
        current = currentDto?.toCurrent() ?: throw IllegalArgumentException("Current data is missing"),
        currentUnits = currentUnitsDto?.toCurrentUnits() ?: throw IllegalArgumentException("Current units data is missing"),
        daily = dailyDto?.toDaily() ?: throw IllegalArgumentException("Daily data is missing"),
        dailyUnits = dailyUnitsDto?.toDailyUnits() ?: throw IllegalArgumentException("Daily units data is missing"),
        hourly = hourlyDto?.toHourly() ?: throw IllegalArgumentException("Hourly data is missing"),
        hourlyUnits = hourlyUnitsDto?.toHourlyUnits() ?: throw IllegalArgumentException("Hourly units data is missing")
    )
}

fun CurrentDto.toCurrent(): Current {
    return Current(
        apparentTemperature = apparentTemperature ?: 0.0,
        isDay = isDay ?: 0,
        precipitationProbability = precipitationProbability ?: 0,
        relativeHumidity2m = relativeHumidity2m ?: 0,
        surfacePressure = surfacePressure ?: 0.0,
        temperature2m = temperature2m ?: 0.0,
        uvIndex = uvIndex ?: 0.0,
        weatherCode = weatherCode ?: 0,
        windSpeed10m = windSpeed10m ?: 0.0
    )
}

fun CurrentUnitsDto.toCurrentUnits(): CurrentUnits {
    return CurrentUnits(
        apparentTemperature = apparentTemperature ?: "",
        precipitationProbability = precipitationProbability ?: "",
        relativeHumidity2m = relativeHumidity2m ?: "",
        surfacePressure = surfacePressure ?: "",
        temperature2m = temperature2m ?: "",
        windSpeed10m = windSpeed10m ?: ""
    )
}

fun DailyDto.toDaily(): Daily {
    return Daily(
        temperature2mMax = temperature2mMax?.filterNotNull() ?: emptyList(),
        temperature2mMin = temperature2mMin?.filterNotNull() ?: emptyList(),
        time = time?.filterNotNull() ?: emptyList(),
        weatherCode = weatherCode?.filterNotNull() ?: emptyList()
    )
}

fun DailyUnitsDto.toDailyUnits(): DailyUnits {
    return DailyUnits(
        temperature2mMax = temperature2mMax ?: "",
        temperature2mMin = temperature2mMin ?: ""
    )
}

fun HourlyDto.toHourly(): Hourly {
    return Hourly(
        temperature2m = temperature2m?.filterNotNull() ?: emptyList(),
        time = time?.filterNotNull() ?: emptyList(),
        weatherCode = weatherCode?.filterNotNull() ?: emptyList()
    )
}

fun HourlyUnitsDto.toHourlyUnits(): HourlyUnits {
    return HourlyUnits(
        temperature2m = temperature2m ?: ""
    )
}