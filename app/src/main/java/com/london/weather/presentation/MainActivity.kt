package com.london.weather.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.london.weather.data.repositories.WeatherRepositoryImpl
import com.london.weather.presentation.theme.WeatherTheme
import kotlinx.coroutines.GlobalScope
import org.koin.android.ext.android.getKoin
import org.koin.core.Koin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherTheme {
                val repository: WeatherRepositoryImpl = getKoin().get()
                LaunchedEffect(key1 = 1) {
                    Log.d("MainActivity", "${repository.getWeatherForecast(40.0, 40.0)}")
                }
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