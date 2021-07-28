package com.locationexplorer.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.locationexplorer.data.database.relation.VenueAndLocation
import com.locationexplorer.data.model.response.Venue
import kotlinx.coroutines.flow.Flow

@Dao
interface VenueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(venue: Venue)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(venues: List<Venue>)

    @Query("Select * from venue")
    fun getVenuesAndLocations(): Flow<VenueAndLocation>

    @Query("Delete from venue")
    suspend fun clear()
}