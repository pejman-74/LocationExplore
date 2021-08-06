package com.locationexplorer.data.model.response.venue_api


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemXX(
    @Json(name = "createdAt")
    val createdAt: Int?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "photo")
    val photo: Photo?
)