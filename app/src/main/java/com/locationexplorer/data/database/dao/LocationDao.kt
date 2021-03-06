package com.locationexplorer.data.database.dao

import androidx.annotation.VisibleForTesting
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.locationexplorer.data.model.database.Location

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: Location)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(locations: List<Location>)

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Query("Select * from location")
    fun getAllLocations(): List<Location>

    @Query("Delete from location")
    suspend fun clear()
}