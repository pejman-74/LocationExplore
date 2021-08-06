package com.locationexplorer.util.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.location.LocationManagerCompat
import com.locationexplorer.LOCATION_UPDATE_RATE
import com.locationexplorer.MIN_DISTANCE_LOCATION_UPDATE_RATE
import com.locationexplorer.data.model.share.SimpleLocation
import com.locationexplorer.data.wapper.LocationObserverStates
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


class LocationObserverImpl constructor(
    context: Context,
    private val ioDispatcher: CoroutineDispatcher
) : LocationObserver {
    private val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("MissingPermission")
    override fun startObserve(): Flow<LocationObserverStates> = callbackFlow {
        val locationListener = object : LocationListener {

            override fun onLocationChanged(location: Location) {
                trySend(
                    LocationObserverStates.LocationChange(
                        SimpleLocation(location.latitude, location.longitude)
                    )
                )
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

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

    private fun locationServiceIsEnabled(): Boolean =
        LocationManagerCompat.isLocationEnabled(locationManager)

    @SuppressLint("MissingPermission")
    override suspend fun currentLocation() =
        suspendCancellableCoroutine<SimpleLocation?> { connotation ->
            if (locationServiceIsEnabled()) {
                LocationManagerCompat.getCurrentLocation(
                    locationManager,
                    LocationManager.GPS_PROVIDER,
                    null,
                    ioDispatcher.asExecutor(),
                    {
                        if (it == null) {
                            connotation.resume(null)
                        } else {
                            connotation.resume(SimpleLocation(it.latitude, it.longitude))
                        }
                    }
                )
            } else {
                val lastKnownLocation =
                    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (lastKnownLocation == null) {
                    connotation.resume(null)
                    return@suspendCancellableCoroutine
                }
                connotation.resume(
                    SimpleLocation(
                        lastKnownLocation.latitude,
                        lastKnownLocation.longitude
                    )
                )
            }
        }

}
