package com.locationexplorer.data.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    @Json(name = "referralId")
    val referralId: String,
    @Json(name = "venue")
    val venue: JsonVenue
)