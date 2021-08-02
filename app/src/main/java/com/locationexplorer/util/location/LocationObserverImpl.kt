package com.locationexplorer.util.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import com.locationexplorer.LOCATION_UPDATE_RATE
import com.locationexplorer.MIN_DISTANCE_LOCATION_UPDATE_RATE
import com.locationexplorer.data.model.share.SimpleLocation
import com.locationexplorer.data.wapper.LocationObserverStates
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


class LocationObserverImpl constructor(private val context: Context) : LocationObserver {
    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("MissingPermission")
    override fun startObserve(): Flow<LocationObserverStates> = callbackFlow {
        val locationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val locationListener = object : LocationListener {

            override fun onLocationChanged(location: Location) {
                trySend(
                    LocationObserverStates.LocationChange(
                        SimpleLocation(location.latitude, location.longitude)
                    )
                )
            }

            override fun onProviderEnabled(provider: String) {
                trySend(LocationObserverStates.ProviderEnabled)
            }

            override fun onProviderDisabled(provider: String) {
                trySend(LocationObserverStates.ProviderDisabled)
            }
        }

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            LOCATION_UPDATE_RATE,
            MIN_DISTANCE_LOCATION_UPDATE_RATE,
            locationListener
        )
        awaitClose {
            locationManager.removeUpdates(locationListener)
        }
    }

}

