package com.locationexplorer.data.model.response


import com.locationexplorer.data.model.database.Venue
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JsonVenue(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "location")
    val location: JsonLocation
) {
    fun toVenue(): Venue = Venue(id, name)
}