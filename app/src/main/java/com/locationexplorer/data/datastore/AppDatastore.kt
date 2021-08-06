package com.locationexplorer.data.datastore

import com.locationexplorer.data.model.share.SimpleLocation

interface AppDatastore {
    suspend fun setUserLastLocation(simpleLocation: SimpleLocation)
    suspend fun getUserLastLocation(): SimpleLocation
    suspend fun setLastUpdateTime(time: Long)
    suspend fun getLastUpdateTime(): Long
    suspend fun clearDataStore()
}