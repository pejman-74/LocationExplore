package com.locationexplorer.data.model.response.venue_api


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListItems(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "items")
    val items: List<ItemXX>?
)