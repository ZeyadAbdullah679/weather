package com.london.weather.presentation.screens.weather

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.android.gms.location.LocationServices
import com.london.weather.presentation.components.ErrorView
import com.london.weather.presentation.components.LoadingView
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