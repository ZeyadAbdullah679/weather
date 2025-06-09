package com.london.weather.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.london.weather.R


val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist)),
        fontWeight = FontWeight(600),
        fontSize = 64.sp, // 24°C
        letterSpacing = 0.25.sp,
        textAlign = TextAlign.Center,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist)),
        fontWeight = FontWeight(600),
        fontSize = 20.sp, // "Today", "Next 7 days"
        letterSpacing = 0.25.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist)),
        fontWeight = FontWeight(500),
        fontSize = 20.sp, // Values like "10 KM/h", "60%", etc.
        textAlign = TextAlign.Center,
        letterSpacing = 0.25.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist)),
        fontWeight = FontWeight(500),
        fontSize = 16.sp, // "Partly cloudy", "Baghdad", time labels, 32°C, etc.
        letterSpacing = 0.25.sp,
        textAlign = TextAlign.Center
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist)),
        fontWeight = FontWeight(400),
        fontSize = 14.sp, // "Feels like", "Wind", "Humidity", etc.
        letterSpacing = 0.25.sp,
        textAlign = TextAlign.Center
    )
)
