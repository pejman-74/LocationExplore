package com.locationexplorer.data.repository

import com.locationexplorer.data.database.relation.VenueAndLocation
import com.locationexplorer.data.model.share.SimpleLocation
import com.locationexplorer.data.wapper.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun explore(
        simpleLocation: SimpleLocation,
        offset: Int = 0,
        forceFetch: Boolean = false,
        isPaginationRequest: Boolean = false,
        currentTime: Long = System.currentTimeMillis(),
        onFetchSuccess: suspend (offset: Int, totalResult: Int) -> Unit = { _, _ -> }
    ): Flow<Resource<List<VenueAndLocation>>>

    suspend fun clearVenuesAndLocations()
    suspend fun setLastUpdate(time: Long)
    suspend fun setLastLocation(simpleLocation: SimpleLocation)
    suspend fun getLastLocation(): SimpleLocation
    fun setTotalResult(count: Int)
    fun setOffset(index: Int)
    fun getTotalResult(): Int
    fun getOffset(): Int
}