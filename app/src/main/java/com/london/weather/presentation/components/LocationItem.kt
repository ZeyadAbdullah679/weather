package com.london.weather.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.london.weather.R
import com.london.weather.presentation.theme.SecondaryTextDark
import com.london.weather.presentation.theme.SecondaryTextLight
import com.london.weather.presentation.utils.ThemePreviews

@Composable
fun LocationItem(
    location: String,
    modifier: Modifier = Modifier,
    isDark: Boolean = isSystemInDarkTheme()
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            painter = painterResource(
                if (isDark) R.drawable.location_night else R.drawable.location_day
            ),
            contentDescription = stringResource(R.string.location_icon),
            tint = Color.Unspecified
        )
        Text(
            text = location,
            style = MaterialTheme.typography.titleMedium,
            color = if (isDark) SecondaryTextDark else SecondaryTextLight
        )
    }
}

@ThemePreviews
@Composable
private fun LocationItemPreview() {
    LocationItem(
        location = "Baghdad",
        modifier = Modifier
    )
}