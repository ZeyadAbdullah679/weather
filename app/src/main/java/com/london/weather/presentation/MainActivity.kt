package com.london.weather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.london.weather.presentation.screens.weather.ScrollTransformLayout
import com.london.weather.presentation.theme.WeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherTheme {
                ScrollTransformLayout()
//                WeatherScreen(context = LocalContext.current)
//                LaunchedEffect(Unit) {
//                    val getWeatherForecastUseCase: GetWeatherForecastUseCase =
//                        getKoin().get()
//                        try {
//                            // Example usage of the use case
//                            val weatherData = getWeatherForecastUseCase(34.0522, -90.0)
//                            Log.d("WeatherData", "Fetched: ${weatherData.collect {
//                                Log.d("WeatherData", it.toString())
//                            }}")
//                        } catch (e: Exception) {
//                            Log.e("WeatherError", "Error fetching weather data", e)
//                        }
//                }
//
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherTheme {

    }
}