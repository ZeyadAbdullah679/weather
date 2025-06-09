package com.london.weather.presentation.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import java.util.Locale

class GPSLocation(
    private val context: Context,
    private val fusedLocationClient: FusedLocationProviderClient
) {

    fun getLocation(
        onSuccess: (Location, String) -> Unit,
        onError: (String) -> Unit
    ) {
        if (!hasLocationPermissions()) {
            onError("Location permissions not granted")
            return
        }

        if (!isLocationEnabled()) {
            onError("Location services are disabled")
            return
        }

        try {
            val locationRequest = CurrentLocationRequest.Builder()
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .build()

            fusedLocationClient.getCurrentLocation(locationRequest, null)
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val cityName = getCityName(location.latitude, location.longitude)
                        onSuccess(location, cityName ?: "Unknown location")
                    } else {
                        onError("Failed to fetch location")
                    }
                }
                .addOnFailureListener { exception ->
                    onError(exception.message ?: "Unexpected error")
                }
        } catch (e: SecurityException) {
            onError("Location permission denied")
        }
    }

    private fun hasLocationPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun getCityName(latitude: Double, longitude: Double): String? {
        return try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            addresses?.firstOrNull()?.locality
        } catch (e: Exception) {
            Log.e("GPSLocation", "Error fetching city name: ${e.message}")
            null
        }
    }
}