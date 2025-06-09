package com.london.weather.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.london.weather.R
import com.london.weather.presentation.theme.PrimaryTextDark
import com.london.weather.presentation.theme.PrimaryTextLight
import com.london.weather.presentation.theme.TertiaryTextDark
import com.london.weather.presentation.theme.TertiaryTextLight
import com.london.weather.presentation.utils.ThemePreviews

@Composable
fun CurrentWeatherCard(
    value: String,
    label: String,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    isDark: Boolean = isSystemInDarkTheme()
) {
    Column(
        modifier
            .height(115.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = if (isDark) Color(0x14FFFFFF) else Color(0x14060414),
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                if (isDark) Color(0xB2060414)
                else Color(0xB2FFFFFF)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = label,
            tint = Color.Unspecified
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            color = if (isDark) PrimaryTextDark else PrimaryTextLight
        )
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = if (isDark) TertiaryTextDark else TertiaryTextLight
        )
    }
}

@ThemePreviews
@Composable
private fun WeatherInfoCardPreview() {
    CurrentWeatherCard(
        value = "25",
        label = "UV",
        icon = if (isSystemInDarkTheme()) R.drawable.uv_night else R.drawable.uv_day,
        modifier = Modifier
    )
}