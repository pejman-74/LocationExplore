package com.locationexplorer.data.model.response.venue_api


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "address")
    val address: String?,
    @Json(name = "cc")
    val cc: String?,
    @Json(name = "city")
    val city: String?,
    @Json(name = "country")
    val country: String?,
    @Json(name = "formattedAddress")
    val formattedAddress: List<String>?,
    @Json(name = "labeledLatLngs")
    val labeledLatLngs: List<LabeledLatLng>?,
    @Json(name = "lat")
    val lat: Double?,
    @Json(name = "lng")
    val lng: Double?,
    @Json(name = "state")
    val state: String?
)