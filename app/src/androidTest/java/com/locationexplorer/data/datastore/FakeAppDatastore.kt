package com.locationexplorer.data.datastore

import com.locationexplorer.data.model.share.SimpleLocation


class FakeAppDatastore : AppDatastore {

    private var lastUserLocation: SimpleLocation? = null
    private var lastUpdateTime = 0L
    override suspend fun setUserLastLocation(simpleLocation: SimpleLocation) {
        lastUserLocation = simpleLocation
    }

    override suspend fun getUserLastLocation(): SimpleLocation {
        return lastUserLocation ?: SimpleLocation(0.0, 0.0)
    }

    override suspend fun setLastUpdateTime(time: Long) {
        lastUpdateTime = time
    }

    override suspend fun getLastUpdateTime(): Long {
        return lastUpdateTime
    }

    override suspend fun clearDataStore() {
        lastUpdateTime = 0L
        lastUserLocation = null
    }

}