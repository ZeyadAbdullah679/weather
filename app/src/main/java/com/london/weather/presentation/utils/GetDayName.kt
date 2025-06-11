package com.london.weather.presentation.utils

import kotlinx.datetime.LocalDate


fun getDayName(dateStr: String): String {
    val date = LocalDate.parse(dateStr)
    return date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
}