package com.london.weather.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.london.weather.R
import com.london.weather.domain.model.Current
import com.london.weather.domain.model.CurrentUnits
import com.london.weather.domain.model.Daily
import com.london.weather.domain.model.DailyUnits
import com.london.weather.presentation.theme.PrimaryTextDark
import com.london.weather.presentation.theme.PrimaryTextLight
import com.london.weather.presentation.theme.TertiaryTextDark
import com.london.weather.presentation.theme.TertiaryTextLight
import com.london.weather.presentation.utils.CloudCoverageMapper
import com.london.weather.presentation.utils.WeatherCodeMapper


@Composable
fun AnimatedWeatherContent(
    scrollState: ScrollState,
    current: Current,
    daily: Daily,
    currentUnits: CurrentUnits,
    dailyUnits: DailyUnits,
    isDark: Boolean
) {
    // Define max scroll for animation
    val maxScroll = 300f
    // Calculate progress based on scroll
    val progress = (scrollState.value / maxScroll).coerceIn(0f, 1f)

    // Animate size and position of weather icon
    val weatherIconSize by animateFloatAsState(targetValue = 250f - (100f * progress))
    val blurEffectSize by animateFloatAsState(targetValue = 226f - (100f * progress))

    // Animate horizontal arrangement
    val horizontalArrangement by animateFloatAsState(
        targetValue = if (progress > 0.5f) 24f else 0f
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(horizontalArrangement.dp)
        ) {
            // Weather Icon Container
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = painterResource(R.drawable.blur_effect),
                    contentDescription = stringResource(R.string.weather_state),
                    modifier = Modifier
                        .width(blurEffectSize.dp)
                        .height((blurEffectSize * 0.9f).dp)
                        .graphicsLayer {
                            alpha = 1f - progress * 0.3f
                        }
                )
                Image(
                    painter = painterResource(
                        WeatherCodeMapper.getWeatherIcon(
                            current.weatherCode,
                            isDark
                        )
                    ),
                    contentDescription = stringResource(R.string.weather_state),
                    modifier = Modifier
                        .width(weatherIconSize.dp)
                        .height(weatherIconSize.dp)
                )
            }

            // Temperature and Details Container
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .weight(1f)
                    .graphicsLayer {
                        translationY = -150f * progress
                    }
            ) {
                Text(
                    text = "${current.temperature2m}${currentUnits.temperature2m}",
                    color = if (isDark) PrimaryTextDark else PrimaryTextLight,
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = lerp(
                            MaterialTheme.typography.displayLarge.fontSize,
                            MaterialTheme.typography.headlineMedium.fontSize,
                            progress
                        )
                    )
                )

                AnimatedVisibility(
                    visible = progress < 0.8f,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = CloudCoverageMapper.getCloudDescription(current.cloudCover),
                            color = if (isDark) TertiaryTextDark else TertiaryTextLight,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.height(4.dp))
                        MaxMinTemperature(
                            maxTemperature = "${daily.temperature2mMax[0]}${dailyUnits.temperature2mMax}",
                            minTemperature = "${daily.temperature2mMin[0]}${dailyUnits.temperature2mMin}"
                        )
                    }
                }
            }
        }
    }
}

// Helper function to interpolate TextUnit values
private fun lerp(start: TextUnit, end: TextUnit, fraction: Float): TextUnit {
    return TextUnit(
        start.value + (end.value - start.value) * fraction,
        start.type
    )
}