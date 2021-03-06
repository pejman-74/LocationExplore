package com.locationexplorer.data.model.response.explorer_api


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExploreResponse(
    @Json(name = "response")
    val response: Response
)