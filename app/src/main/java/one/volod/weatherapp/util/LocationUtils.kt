package one.volod.weatherapp.util

import android.content.Context
import android.location.LocationManager
import one.volod.weatherapp.domain.model.Coordinates

class LocationUtils(
    private val context: Context
) {

    @Suppress("MissingPermission")
    fun getCurrentLocation(): Coordinates {
        if (!context.hasLocationPermission()) {
            throw SecurityException("Location permission not granted")
        }

        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (!isGpsEnabled && !isNetworkEnabled) {
            throw IllegalStateException("Location not enabled")
        }

        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)


        val coordinates = location?.let {
            Coordinates(it.latitude, it.longitude)
        } ?: throw IllegalStateException("Location not found")

        return coordinates
    }
}