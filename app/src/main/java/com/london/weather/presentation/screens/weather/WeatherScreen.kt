package com.london.weather.presentation.screens.weather

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.location.LocationServices
import com.london.weather.presentation.utils.GPSLocation
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    context: Context
) {
    val viewModel: WeatherViewModel = koinViewModel()
    val gpsLocation = GPSLocation(
        context = context,
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    )
    val coroutineScope = rememberCoroutineScope()

    val weatherUiState by viewModel.weatherUiState.collectAsState()

    val requestLocationPermissions =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                coroutineScope.launch {
                    gpsLocation.getLocation(
                        onSuccess = { location, cityName ->
                            viewModel.getWeatherInfo(location.latitude, location.longitude, cityName)
                        },
                        onError = { errorMessage ->
                            viewModel.updateUiState(WeatherUiState.Error(errorMessage))
                        }
                    )
                }
            } else {
                viewModel.updateUiState(WeatherUiState.Error("Location permission denied"))
            }
        }

    LaunchedEffect(Unit) {
        requestLocationPermissions.launch(ACCESS_COARSE_LOCATION)
    }

    when (val state = weatherUiState) {
        is WeatherUiState.Success -> WeatherScreenContent(state, modifier)
        is WeatherUiState.Error -> ErrorView(state.message) {
            requestLocationPermissions.launch(ACCESS_COARSE_LOCATION)
        }
        WeatherUiState.Loading -> LoadingView()
    }
}



@Composable
fun ErrorView(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Error: $message", color = MaterialTheme.colorScheme.error)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}