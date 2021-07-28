package com.locationexplorer.data.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(
    @Json(name = "groups")
    val groups: List<Group>,
    @Json(name = "headerFullLocation")
    val headerFullLocation: String,
    @Json(name = "headerLocation")
    val headerLocation: String,
    @Json(name = "headerLocationGranularity")
    val headerLocationGranularity: String,
    @Json(name = "suggestedRadius")
    val suggestedRadius: Int,
    @Json(name = "totalResults")
    val totalResults: Int
)