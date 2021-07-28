package com.locationexplorer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.locationexplorer.data.database.converter.StringListTypeConverter
import com.locationexplorer.data.database.dao.LocationDao
import com.locationexplorer.data.database.dao.VenueDao
import com.locationexplorer.data.model.response.Location
import com.locationexplorer.data.model.response.Venue

@Database(entities = [Venue::class, Location::class], version = 1)
@TypeConverters(StringListTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun venueDao(): VenueDao
    abstract fun locationDao(): LocationDao
}