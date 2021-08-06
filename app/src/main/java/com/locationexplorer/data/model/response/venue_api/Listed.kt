package com.locationexplorer.data.model.response.venue_api


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Listed(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "groups")
    val groups: List<GroupXX>?
)