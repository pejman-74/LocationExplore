package com.locationexplorer.data.model.response.venue_api


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VenueResponse(
    @Json(name = "meta")
    val meta: Meta?,
    @Json(name = "response")
    val response: Response?
)