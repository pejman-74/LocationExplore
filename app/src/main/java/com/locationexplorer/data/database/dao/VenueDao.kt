package com.locationexplorer.data.database.dao

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.NONE
import androidx.room.*
import com.locationexplorer.data.database.relation.VenueAndLocation
import com.locationexplorer.data.model.database.Venue
import kotlinx.coroutines.flow.Flow

@Dao
interface VenueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(venue: Venue)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(venues: List<Venue>)

    @Query("Select * from venue")
    @Transaction
    fun getVenuesAndLocations(): Flow<List<VenueAndLocation>>

    @VisibleForTesting(otherwise = NONE)
    @Query("Select * from venue")
    suspend fun getAllVenues(): List<Venue>

    @Query("Delete from venue")
    suspend fun clear()
}