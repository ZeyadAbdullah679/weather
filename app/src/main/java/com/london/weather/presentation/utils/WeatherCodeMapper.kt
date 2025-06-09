package com.london.weather.presentation.utils

import androidx.annotation.DrawableRes
import com.london.weather.R

object WeatherCodeMapper {
    private val weatherIconMap = mapOf(
        0 to Pair(R.drawable.clear_sky_day, R.drawable.clear_sky_night),
        1 to Pair(R.drawable.mainly_clear_day, R.drawable.mainly_clear_night),
        2 to Pair(R.drawable.partly_cloudy_day, R.drawable.partly_cloudy_night),
        3 to Pair(R.drawable.overcast_day, R.drawable.overcast_night),
        45 to Pair(R.drawable.fog_day, R.drawable.fog_night),
        48 to Pair(R.drawable.depositing_rime_fog_day, R.drawable.depositing_rime_fog_night),
        51 to Pair(R.drawable.drizzle_light_day, R.drawable.drizzle_light_night),
        53 to Pair(R.drawable.drizzle_moderate_day, R.drawable.drizzle_moderate_night),
        55 to Pair(R.drawable.drizzle_intensity_day, R.drawable.drizzle_intensity_night),
        56 to Pair(R.drawable.freezing_drizzle_light_day, R.drawable.freezing_drizzle_light_night),
        57 to Pair(R.drawable.freezing_drizzle_intensity_day, R.drawable.freezing_drizzle_intensity_night),
        61 to Pair(R.drawable.rain_slight_day, R.drawable.rain_slight_night),
        63 to Pair(R.drawable.rain_moderate_day, R.drawable.rain_moderate_night),
        65 to Pair(R.drawable.rain_intensity_day, R.drawable.rain_intensity_night),
        66 to Pair(R.drawable.freezing_light_day, R.drawable.freezing_light_night),
        67 to Pair(R.drawable.freezing_heavy_day, R.drawable.freezing_heavy_night),
        71 to Pair(R.drawable.snow_fall_light_day, R.drawable.snow_fall_light_night),
        73 to Pair(R.drawable.snow_fall_moderate_day, R.drawable.snow_fall_moderate_night),
        75 to Pair(R.drawable.snow_fall_intensity_day, R.drawable.snow_fall_intensity_night),
        77 to Pair(R.drawable.snow_grains_day, R.drawable.snow_grains_night),
        80 to Pair(R.drawable.rain_shower_slight_day, R.drawable.rain_shower_slight_night),
        81 to Pair(R.drawable.rain_shower_moderate_day, R.drawable.rain_shower_moderate_night),
        82 to Pair(R.drawable.rain_shower_violent_day, R.drawable.rain_shower_violent_night),
        85 to Pair(R.drawable.snow_shower_slight_day, R.drawable.snow_shower_slight_night),
        86 to Pair(R.drawable.snow_shower_heavy_day, R.drawable.snow_shower_heavy_night),
        95 to Pair(R.drawable.thunderstrom_slight_or_moderate_day, R.drawable.thunderstrom_slight_or_moderate_day),
        96 to Pair(R.drawable.thunderstrom_with_slight_hail_night, R.drawable.thunderstrom_with_slight_hail_day),
        99 to Pair(R.drawable.thunderstrom_with_heavy_hail_day, R.drawable.thunderstrom_with_heavy_hail_night)
    )

    /**
     * Get the weather icon resource ID based on the weather code and time of day
     * @param code The WMO weather code
     * @param isDay Boolean indicating if it's daytime (true) or nighttime (false)
     * @return The drawable resource ID for the weather icon
     */
    @DrawableRes
    fun getWeatherIcon(code: Int, isDay: Boolean): Int {
        val iconPair = weatherIconMap[code]
        return when {
            iconPair != null && isDay -> iconPair.first
            iconPair != null && !isDay -> iconPair.second
            else -> R.drawable.clear_sky_day // Default icon if code not found
        }
    }
}