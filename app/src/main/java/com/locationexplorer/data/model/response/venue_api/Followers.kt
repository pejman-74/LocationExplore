package com.locationexplorer.data.model.response.venue_api


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Followers(
    @Json(name = "count")
    val count: Int?
)