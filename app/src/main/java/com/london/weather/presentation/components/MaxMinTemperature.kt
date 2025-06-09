package com.london.weather.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.london.weather.R
import com.london.weather.presentation.theme.SecondaryTextDark
import com.london.weather.presentation.theme.SecondaryTextLight
import com.london.weather.presentation.utils.ThemePreviews

@Composable
fun MaxMinTemperature(
    maxTemperature: String,
    minTemperature: String,
    modifier: Modifier = Modifier,
    isDark: Boolean = isSystemInDarkTheme()
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = modifier
            .height(35.dp)
            .background(color = Color(0x14FFFFFF), shape = RoundedCornerShape(size = 100.dp))
            .padding(horizontal = 24.dp, vertical = 8.dp)
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

        Canvas(modifier = Modifier.fillMaxHeight()) {
            drawLine(
                color = if (isDark) Color(0x3DFFFFFF) else Color(0x3D060414),
                start = Offset(0f, 15f),
                end = Offset(size.width, size.height),
                cap = StrokeCap.Round,
                strokeWidth = 4f
            )
        }

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


@ThemePreviews
@Composable
private fun MaxMinTemperaturePreview() {
    MaxMinTemperature(
        maxTemperature = "32°C",
        minTemperature = "20°C",
        modifier = Modifier
    )
}