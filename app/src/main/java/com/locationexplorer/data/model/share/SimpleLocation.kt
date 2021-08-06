package com.locationexplorer.data.model.share

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SimpleLocation(
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "long")
    val long: Double
)
