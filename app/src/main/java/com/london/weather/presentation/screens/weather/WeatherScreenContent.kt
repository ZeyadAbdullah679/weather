package com.london.weather.presentation.screens.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.london.weather.R
import com.london.weather.domain.model.Current
import com.london.weather.domain.model.CurrentUnits
import com.london.weather.domain.model.Daily
import com.london.weather.domain.model.DailyUnits
import com.london.weather.domain.model.Hourly
import com.london.weather.domain.model.HourlyUnits
import com.london.weather.domain.model.WeatherForecast
import com.london.weather.presentation.components.AnimatedWeatherContent
import com.london.weather.presentation.components.CurrentWeatherCard
import com.london.weather.presentation.components.LocationItem
import com.london.weather.presentation.utils.ThemePreviews

@Composable
fun WeatherScreenContent(
    state: WeatherUiState.Success,
    modifier: Modifier = Modifier
) {
    val isDark = state.weatherForecast.current.isDay == 1
    val current = state.weatherForecast.current
    val currentUnits = state.weatherForecast.currentUnits
    val daily = state.weatherForecast.daily
    val dailyUnits = state.weatherForecast.dailyUnits
    val hourly = state.weatherForecast.hourly
    val hourlyUnits = state.weatherForecast.hourlyUnits

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    if (isDark) listOf(Color(0xFF060414), Color(0xFF0D0C19))
                    else listOf(Color(0xFF87CEFA), Color(0xFFFFFFFF))
                )
            )
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // City, Temperature & Status
        Spacer(modifier = Modifier.height(10.dp))

        LocationItem(state.cityName, isDark = isDark)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedWeatherContent(
                scrollState = scrollState,
                current = current,
                daily = daily,
                currentUnits = currentUnits,
                dailyUnits = dailyUnits,
                isDark = isDark
            )
//            {
////                Box(contentAlignment = Alignment.Center) {
////                    Image(
////                        painter = painterResource(R.drawable.blur_effect),
////                        contentDescription = stringResource(R.string.weather_state),
////                        modifier = Modifier
////                            .width(226.dp)
////                            .height(200.dp)
////                    )
////                    Image(
////                        painter = painterResource(
////                            WeatherCodeMapper.getWeatherIcon(
////                                current.weatherCode,
////                                isDark
////                            )
////                        ), // Your weather icon
////                        contentDescription = stringResource(R.string.weather_state),
////                        modifier = Modifier
////                            .width(250.dp)
////                            .height(250.dp)
////                    )
////                }
////
////                Spacer(Modifier.width(24.dp))
////                Column(
////                    verticalArrangement = Arrangement.Center,
////                    horizontalAlignment = Alignment.CenterHorizontally
////                ) {
////                    Text(
////                        text = "${current.temperature2m}${currentUnits.temperature2m}",
////                        color = if (isDark) PrimaryTextDark else PrimaryTextLight,
////                        style = MaterialTheme.typography.displayLarge
////                    )
////                    Text(
////                        text = CloudCoverageMapper.getCloudDescription(current.cloudCover),
////                        color = if (isDark) TertiaryTextDark else TertiaryTextLight,
////                        style = MaterialTheme.typography.titleMedium
////                    )
////                    Spacer(Modifier.height(4.dp))
////                    MaxMinTemperature(
////                        maxTemperature = "${daily.temperature2mMax[0]}${dailyUnits.temperature2mMax}",
////                        minTemperature = "${daily.temperature2mMin[0]}${dailyUnits.temperature2mMin}"
////                    )
////                }
//            }
        }

        // Weather Info Cards
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            CurrentWeatherCard(
                value = current.windSpeed10m.toString() + currentUnits.windSpeed10m,
                label = stringResource(R.string.wind),
                icon = if (isDark) R.drawable.fast_wind_night else R.drawable.fast_wind_day,
                modifier = Modifier.weight(1f),
                isDark = isDark
            )
            CurrentWeatherCard(
                value = current.relativeHumidity2m.toString() + currentUnits.relativeHumidity2m,
                label = stringResource(R.string.humidity),
                icon = if (isDark) R.drawable.humidity_night else R.drawable.humidity_day,
                modifier = Modifier.weight(1f),
                isDark = isDark
            )
            CurrentWeatherCard(
                value = current.precipitationProbability.toString() + currentUnits.precipitationProbability,
                label = stringResource(R.string.rain),
                icon = if (isDark) R.drawable.rain_night else R.drawable.rain_day,
                modifier = Modifier.weight(1f),
                isDark = isDark
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            CurrentWeatherCard(
                value = current.uvIndex.toString(),
                label = stringResource(R.string.uv_index),
                icon = if (isDark) R.drawable.uv_night else R.drawable.uv_day,
                modifier = Modifier.weight(1f),
                isDark = isDark
            )
            CurrentWeatherCard(
                value = current.surfacePressure.toString() + currentUnits.surfacePressure,
                label = stringResource(R.string.pressure),
                icon = if (isDark) R.drawable.pressure_night else R.drawable.pressure_day,
                modifier = Modifier.weight(1f),
                isDark = isDark
            )
            CurrentWeatherCard(
                value = current.apparentTemperature.toString() + currentUnits.apparentTemperature,
                label = stringResource(R.string.feels_like),
                icon = if (isDark) R.drawable.temperature_night else R.drawable.temperature_day,
                modifier = Modifier.weight(1f),
                isDark = isDark
            )
        }


    }
}


@ThemePreviews
@Composable
private fun WeatherScreenPreview() {
    WeatherScreenContent(
        state = WeatherUiState.Success(
            weatherForecast = WeatherForecast(
                current = Current(
                    apparentTemperature = 25.0,
                    isDay = 1,
                    precipitationProbability = 10,
                    relativeHumidity2m = 60,
                    surfacePressure = 1013.0,
                    temperature2m = 24.0,
                    uvIndex = 5.0,
                    weatherCode = 1,
                    windSpeed10m = 15.0,
                    cloudCover = 23.0
                ),
                currentUnits = CurrentUnits(
                    apparentTemperature = "°C",
                    precipitationProbability = "%",
                    relativeHumidity2m = "%",
                    surfacePressure = "hPa",
                    temperature2m = "°C",
                    windSpeed10m = "km/h",
                    cloudCover = "%"
                ),
                daily = Daily(
                    temperature2mMax = listOf(30.0, 28.0, 27.0, 26.0, 25.0, 24.0, 23.0),
                    temperature2mMin = listOf(20.0, 19.0, 18.0, 17.0, 16.0, 15.0, 14.0),
                    time = listOf(
                        "2023-10-01",
                        "2023-10-02",
                        "2023-10-03",
                        "2023-10-04",
                        "2023-10-05",
                        "2023-10-06",
                        "2023-10-07"
                    ),
                    weatherCode = listOf(1, 2, 3, 1, 2, 3, 1)
                ),
                dailyUnits = DailyUnits(
                    temperature2mMax = "°C",
                    temperature2mMin = "°C"
                ),
                hourly = Hourly(
                    temperature2m = listOf(24.0, 25.0, 26.0, 27.0, 28.0),
                    time = listOf("10:00", "11:00", "12:00", "13:00", "14:00"),
                    weatherCode = listOf(1, 2, 3, 1, 2)
                ),
                hourlyUnits = HourlyUnits(
                    temperature2m = "°C"
                )
            ),
            longitude = 23.0,
            latitude = 23.0,
            cityName = "Cairo"
        )
    )
}