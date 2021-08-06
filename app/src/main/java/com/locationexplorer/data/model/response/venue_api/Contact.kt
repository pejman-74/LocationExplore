package com.locationexplorer.data.model.response.venue_api


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Contact(
    @Json(name = "facebook")
    val facebook: String?,
    @Json(name = "facebookName")
    val facebookName: String?,
    @Json(name = "facebookUsername")
    val facebookUsername: String?,
    @Json(name = "formattedPhone")
    val formattedPhone: String?,
    @Json(name = "instagram")
    val instagram: String?,
    @Json(name = "phone")
    val phone: String?,
    @Json(name = "twitter")
    val twitter: String?
)