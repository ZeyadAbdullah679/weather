package com.london.weather.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.london.weather.R
import com.london.weather.presentation.theme.PrimaryTextDark
import com.london.weather.presentation.theme.PrimaryTextLight
import com.london.weather.presentation.utils.WeatherCodeMapper.getWeatherIcon

@Composable
fun TodayWeatherHourItems(
    timeList: List<String>,
    temperatureList: List<String>,
    code: List<Int>,
    isDark: Boolean = isSystemInDarkTheme()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.today),
            color = if (isDark) PrimaryTextDark else PrimaryTextLight,
            style = MaterialTheme.typography.headlineMedium
        )
        LazyRow(
            modifier = Modifier
                    .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(timeList.size) {
                Forecast(
                    time = timeList[it].substring(11, 16),
                    temperature = temperatureList[it],
                    icon = getWeatherIcon(isDay = isDark, code = code[it])
                )
            }
        }
    }
}


@Preview
@Composable
fun TodayWeatherHourItemsPreview() {
    TodayWeatherHourItems(
        timeList = listOf(
            "2025-06-10T10:00",
            "2025-06-10T11:00",
            "2025-06-10T12:00",
            "2025-06-10T13:00",
            "2025-06-10T14:00",
            "2025-06-10T15:00",
            "2025-06-10T16:00",
            "2025-06-10T17:00",
            "2025-06-10T18:00",
            "2025-06-10T19:00",
            "2025-06-10T20:00",
            "2025-06-10T21:00"
        ),

        temperatureList = listOf(
            "20",
            "21",
            "22",
            "23",
            "24",
            "25",
            "26",
            "27",
            "28",
            "29",
            "30",
            "31"
        ),

        code = listOf(
            3,
            3,
            61,
            61,
            61,
            3,
            3,
            61,
            61,
            80,
            80,
            3
        ),

        isDark = true
    )
}