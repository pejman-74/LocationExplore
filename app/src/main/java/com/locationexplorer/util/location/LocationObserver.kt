package com.locationexplorer.util.location

import com.locationexplorer.data.model.share.SimpleLocation
import com.locationexplorer.data.wapper.LocationObserverStates
import kotlinx.coroutines.flow.Flow

interface LocationObserver {
    fun startObserve(): Flow<LocationObserverStates>
    suspend fun currentLocation() : SimpleLocation?
}