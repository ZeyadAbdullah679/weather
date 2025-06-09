package com.london.weather.presentation.utils

object CloudCoverageMapper {
    // Map ranges of cloud coverage percentages to descriptive strings
    private val cloudCoverageMap = listOf(
        0..10 to "Clear Sky",
        11..25 to "Mostly Clear",
        26..50 to "Partly Cloudy",
        51..75 to "Mostly Cloudy",
        76..89 to "Considerable Cloudiness",
        90..100 to "Overcast"
    )

    /**
     * Get cloud coverage description based on percentage
     * @param percentage Cloud coverage percentage (0-100)
     * @return Description of cloud coverage
     */
    fun getCloudDescription(percentage: Int): String {
        // Ensure percentage is within valid range
        val validPercentage = percentage.coerceIn(0, 100)

        return cloudCoverageMap.find { (range, _) ->
            validPercentage in range
        }?.second ?: "Unknown"
    }
}