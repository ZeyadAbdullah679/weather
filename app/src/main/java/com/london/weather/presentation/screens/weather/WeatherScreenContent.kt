package com.london.weather.presentation.screens.weather

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
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
import com.london.weather.presentation.components.CurrentWeatherCard
import com.london.weather.presentation.components.LocationItem
import com.london.weather.presentation.components.MaxMinTemperature
import com.london.weather.presentation.components.TodayWeatherHourItems
import com.london.weather.presentation.components.UpcomingWeatherItems
import com.london.weather.presentation.theme.PrimaryTextDark
import com.london.weather.presentation.theme.PrimaryTextLight
import com.london.weather.presentation.theme.TertiaryTextDark
import com.london.weather.presentation.theme.TertiaryTextLight
import com.london.weather.presentation.utils.CloudCoverageMapper
import com.london.weather.presentation.utils.ThemePreviews
import com.london.weather.presentation.utils.WeatherCodeMapper
import kotlin.math.roundToInt

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
    val density = LocalDensity.current

    val scrollProgress by remember {
        derivedStateOf {
            with(density) {
                (scrollState.value / 30.dp.toPx()).coerceIn(0f, .9f)
            }
        }
    }

    val imageScale by animateFloatAsState(
        targetValue = 1f - (0.1f * scrollProgress),
        animationSpec = tween(durationMillis = 200, easing = LinearOutSlowInEasing),
        label = "imageScale"
    )

    val imageOffsetX by animateFloatAsState(
        targetValue = -10f * scrollProgress,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "imageOffsetX"
    )

    val columnOffsetX by animateFloatAsState(
        targetValue = 10f * scrollProgress,
        animationSpec = tween(durationMillis = 200, easing = LinearOutSlowInEasing),
        label = "columnOffsetX"
    )
    val columnOffsetY by animateFloatAsState(
        targetValue = -20f * scrollProgress,
        animationSpec = tween(durationMillis = 200, easing = LinearOutSlowInEasing),
        label = "columnOffsetY"
    )


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    if (isDark) listOf(Color(0xFF060414), Color(0xFF0D0C19))
                    else listOf(Color(0xFF87CEFA), Color(0xFFFFFFFF))
                )
            )
            .padding(vertical = 26.dp, horizontal = 16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        LocationItem(state.cityName, isDark = isDark)
        Spacer(modifier = Modifier.height(12.dp))

        // Use a Box to overlay both layouts and animate between them
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    // Smoothly transition from stacked height to side-by-side height
                    (350.dp * (1f - scrollProgress)) + (200.dp * scrollProgress)
                ),
            contentAlignment = Alignment.TopCenter
        ) {
            // Weather image with animated position
            Image(
                painter = painterResource(
                    WeatherCodeMapper.getWeatherIcon(
                        current.weatherCode,
                        isDark
                    )
                ),
                contentDescription = stringResource(R.string.weather_state),
                modifier = Modifier
                    .size(
                        width = with(density) { (220.dp.toPx() * imageScale).toDp() },
                        height = with(density) { (200.dp.toPx() * imageScale).toDp() }
                    )
                    .offset(
                        x = imageOffsetX.dp - (130.dp * scrollProgress), // Move left
                        y = 0.dp
                    )
            )

            // Weather info column with animated position
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .offset(
                        x = columnOffsetX.dp + (130.dp * scrollProgress), // Move right
                        y = (200.dp * (1f - scrollProgress)) + columnOffsetY.dp // Start below, move up
                    )
            ) {
                Text(
                    text = "${current.temperature2m.roundToInt()}${currentUnits.temperature2m}",
                    color = if (isDark) PrimaryTextDark else PrimaryTextLight,
                    style = MaterialTheme.typography.displayLarge
                )
                Text(
                    text = CloudCoverageMapper.getCloudDescription(current.cloudCover),
                    color = if (isDark) TertiaryTextDark else TertiaryTextLight,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(12.dp))
                MaxMinTemperature(
                    maxTemperature = "${daily.temperature2mMax[0].roundToInt()}${dailyUnits.temperature2mMax}",
                    minTemperature = "${daily.temperature2mMin[0].roundToInt()}${dailyUnits.temperature2mMin}",
                    isDark = isDark
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            CurrentWeatherCard(
                value = current.windSpeed10m.toString() + " KM/h",
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
                value = "${current.surfacePressure} ${currentUnits.surfacePressure}",
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

        Spacer(modifier = Modifier.height(24.dp))

        TodayWeatherHourItems(
            timeList = hourly.time.take(24),
            temperatureList = hourly.temperature2m.map {
                it.roundToInt().toString() + hourlyUnits.temperature2m
            }.take(24),
            code = hourly.weatherCode.take(24),
            isDark = isDark
        )

        Spacer(modifier = Modifier.height(24.dp))

        UpcomingWeatherItems(
            days = daily.time,
            minTemperatures = daily.temperature2mMin.map {
                it.roundToInt().toString() + dailyUnits.temperature2mMin
            },
            maxTemperatures = daily.temperature2mMax.map {
                it.roundToInt().toString() + dailyUnits.temperature2mMax
            },
            weatherCodes = daily.weatherCode,
            isDark = isDark
        )
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