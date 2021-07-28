package com.locationexplorer.data.model.response


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
data class Venue(
    @Json(name = "id")
    @PrimaryKey
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "location")
    @Ignore
    val location: Location
)