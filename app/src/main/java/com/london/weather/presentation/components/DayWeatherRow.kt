package com.london.weather.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DayWeatherRow(day: String, iconRes: Int, temps: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFF232347), RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(day, color = Color.White, fontSize = 16.sp, modifier = Modifier.weight(1.5f))
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = day,
            tint = Color.White,
            modifier = Modifier.size(22.dp)
        )
        Spacer(Modifier.weight(0.5f))
        Text(temps, color = Color(0xFF8689A3), fontSize = 14.sp)
    }
}