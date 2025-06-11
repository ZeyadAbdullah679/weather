package com.london.weather.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.london.weather.R
import com.london.weather.presentation.theme.PrimaryTextDark
import com.london.weather.presentation.theme.PrimaryTextLight
import com.london.weather.presentation.utils.getDayName

@Composable
fun UpcomingWeatherItems(
    modifier: Modifier = Modifier,
    days: List<String>,
    minTemperatures: List<String>,
    maxTemperatures: List<String>,
    weatherCodes: List<Int>,
    isDark: Boolean = isSystemInDarkTheme()
) {
    Column {
        Text(
            text = stringResource(R.string.next_7_days),
            color = if (isDark) PrimaryTextDark else PrimaryTextLight,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(12.dp))

        Column(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = if (isDark) Color(0x14FFFFFF) else Color(0x14060414),
                    shape = RoundedCornerShape(size = 24.dp)
                )
                .background(
                    color = if (isDark) Color(0xB2060414) else Color(0xB2FFFFFF),
                    shape = RoundedCornerShape(size = 24.dp)
                )
                .padding(vertical = 4.dp)
        ) {
            repeat(days.size) { index ->
                UpcomingWeatherCard(
                    day = getDayName(days[index]) ,
                    minTemperature = minTemperatures[index],
                    maxTemperature = maxTemperatures[index],
                    weatherCode = weatherCodes[index],
                    modifier = Modifier,
                    isDark = isDark
                )
                if (index < days.size - 1) {
                    HorizontalDivider(
                        color = if (isDark) Color(0x14FFFFFF) else Color(0x14060414),
                        thickness = 1.dp
                    )
                }
            }
        }
    }

}

@Preview
@Composable
private fun UpcomingWeatherItemsPreview() {
    UpcomingWeatherItems(
        days = listOf("Mon", "Tue", "Wed", "Thu", "Fri"),
        minTemperatures = listOf("10°C", "11°C", "12°C", "13°C", "14°C"),
        maxTemperatures = listOf("20°C", "21°C", "22°C", "23°C", "24°C"),
        weatherCodes = listOf(0, 0, 0, 0, 0),
        isDark = true
    )
}