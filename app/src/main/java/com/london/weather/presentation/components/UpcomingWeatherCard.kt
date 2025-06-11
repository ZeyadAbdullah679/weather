package com.london.weather.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.london.weather.R
import com.london.weather.presentation.theme.SecondaryTextDark
import com.london.weather.presentation.theme.SecondaryTextLight
import com.london.weather.presentation.theme.TertiaryTextDark
import com.london.weather.presentation.theme.TertiaryTextLight
import com.london.weather.presentation.utils.WeatherCodeMapper

@Composable
fun UpcomingWeatherCard(
    day: String,
    minTemperature: String,
    maxTemperature: String,
    weatherCode: Int,
    modifier: Modifier = Modifier,
    isDark: Boolean = isSystemInDarkTheme()
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Text(
            text = day,
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            ),
            color = if (isDark) TertiaryTextDark else TertiaryTextLight,
            modifier = Modifier.weight(2f)
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(WeatherCodeMapper.getWeatherIcon(weatherCode, isDark)),
            contentDescription = stringResource(R.string.weather_icon),
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Icon(
                painter = painterResource(if (isDark) R.drawable.arrow_up_night else R.drawable.arrow_up_day),
                contentDescription = stringResource(R.string.max_temperature),
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = maxTemperature,
                style = MaterialTheme.typography.titleMedium,
                color = if (isDark) SecondaryTextDark else SecondaryTextLight
            )

            Spacer(modifier = Modifier.width(8.dp))

            VerticalDivider(
                color = if (isDark) Color(0x3DFFFFFF) else Color(0x3D060414),
                thickness = 1.dp,
                modifier = Modifier.height(18.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                painter = painterResource(if (isDark) R.drawable.arrow_down_night else R.drawable.arrow_down_day),
                contentDescription = stringResource(R.string.max_temperature),
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = minTemperature,
                style = MaterialTheme.typography.titleMedium,
                color = if (isDark) SecondaryTextDark else SecondaryTextLight
            )
        }
    }

}

@Preview
@Composable
private fun UpcomingWeatherCardPreview() {
    UpcomingWeatherCard(
        modifier = Modifier,
        day = "Monday",
        minTemperature = "15°C",
        maxTemperature = "25°C",
        weatherCode = 0
    )
}