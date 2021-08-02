package com.locationexplorer.data.repository

import androidx.room.withTransaction
import com.locationexplorer.DATA_REFRESH_RATE
import com.locationexplorer.data.api.ExploreApi
import com.locationexplorer.data.database.AppDatabase
import com.locationexplorer.data.database.relation.VenueAndLocation
import com.locationexplorer.data.datastore.AppDatastore
import com.locationexplorer.data.holder.IndexHolder
import com.locationexplorer.data.model.share.SimpleLocation
import com.locationexplorer.data.wapper.Resource
import com.locationexplorer.util.ext.networkBoundResource
import com.locationexplorer.util.network.ConnectionChecker
import kotlinx.coroutines.flow.Flow


class RepositoryImpl(
    private val exploreApi: ExploreApi,
    private val appDatabase: AppDatabase,
    private val appDatastore: AppDatastore,
    private val connectionChecker: ConnectionChecker,
    private val indexHolder: IndexHolder,
) : Repository {

    private val venueDao = appDatabase.venueDao()
    private val locationDao = appDatabase.locationDao()

    override fun explore(
        simpleLocation: SimpleLocation,
        offset: Int,
        forceFetch: Boolean,
        isPaginationRequest: Boolean,
        currentTime: Long,
        onFetchSuccess: suspend (offset: Int, totalResult: Int) -> Unit
    ): Flow<Resource<List<VenueAndLocation>>> {

        return networkBoundResource(
            query = {
                venueDao.getVenuesAndLocations()
            },
            fetch = {
                exploreApi.explore("${simpleLocation.lat},${simpleLocation.long}", offset)
            },
            saveFetchResult = { explorerResponse ->
                //notify last indexes
                onFetchSuccess(offset, explorerResponse.response.totalResults)

                val venues = explorerResponse.response.groups.first().items.map { item ->
                    item.venue.toVenue()
                }

                val locations = explorerResponse.response.groups.first().items.map { item ->
                    item.venue.location.toLocation().copy(venueId = item.venue.id)
                }
                appDatabase.withTransaction {
                    if (!isPaginationRequest)
                        clearVenuesAndLocations()

                    venueDao.insert(venues)
                    locationDao.insert(locations)
                }
            },
            shouldFetch = {
                if (it.isEmpty())
                    return@networkBoundResource true

                if (forceFetch)
                    return@networkBoundResource true

                /** challenge conditions*/
                // is has connection
                if (connectionChecker.isConnectionAvailable())
                    return@networkBoundResource true

                // data's is old
                if (currentTime - appDatastore.getLastUpdateTime() >= DATA_REFRESH_RATE)
                    return@networkBoundResource true

                // user location is changed
                val userLastLocation = appDatastore.getUserLastLocation()
                if (userLastLocation.lat != simpleLocation.lat &&
                    userLastLocation.long != simpleLocation.long
                )
                    return@networkBoundResource true

                false
            }
        )
    }

    override suspend fun clearVenuesAndLocations() {
        venueDao.clear()
        locationDao.clear()
    }

    override suspend fun setLastLocation(simpleLocation: SimpleLocation) {
        appDatastore.setUserLastLocation(simpleLocation)
    }

    override suspend fun setLastUpdate(time: Long) {
        appDatastore.setLastUpdateTime(time)
    }

    override fun setTotalResult(count: Int) {
        indexHolder.currentTotalResult = count
    }

    override fun setOffset(index: Int) {
        indexHolder.currentOffset = index
    }

    override fun getTotalResult(): Int {
        return indexHolder.currentTotalResult
    }

    override fun getOffset(): Int {
        return indexHolder.currentOffset
    }

}


