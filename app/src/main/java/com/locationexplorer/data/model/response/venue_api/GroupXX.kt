package com.locationexplorer.data.model.response.venue_api


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupXX(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "items")
    val items: List<ItemX>?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "type")
    val type: String?
)