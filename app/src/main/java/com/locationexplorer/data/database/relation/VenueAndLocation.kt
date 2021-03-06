package com.locationexplorer.data.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.locationexplorer.data.model.database.Location
import com.locationexplorer.data.model.database.Venue


data class VenueAndLocation(
    @Embedded
    val venue: Venue,
    @Relation(parentColumn = "id", entityColumn = "venueId")
    val location: Location
)