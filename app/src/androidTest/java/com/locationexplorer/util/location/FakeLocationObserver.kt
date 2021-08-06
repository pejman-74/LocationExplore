package com.locationexplorer.util.location

import com.locationexplorer.data.model.share.SimpleLocation
import com.locationexplorer.data.wapper.LocationObserverStates
import com.simpleLocation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

@OptIn(ExperimentalCoroutinesApi::class)
class FakeLocationObserver : LocationObserver {
    var isEmitLocation = true
    var emitInterval = 1000L
    var currentLocation: SimpleLocation? = simpleLocation
    override fun startObserve(): Flow<LocationObserverStates> = channelFlow {
        while (isEmitLocation) {
            send(LocationObserverStates.LocationChange(simpleLocation))
            delay(emitInterval)
        }
    }

    override suspend fun currentLocation(): SimpleLocation? = currentLocation

}