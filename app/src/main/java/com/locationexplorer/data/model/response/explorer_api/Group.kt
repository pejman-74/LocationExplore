package com.locationexplorer.data.model.response.explorer_api


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Group(
    @Json(name = "items")
    val items: List<Item>,
    @Json(name = "name")
    val name: String,
    @Json(name = "type")
    val type: String
)