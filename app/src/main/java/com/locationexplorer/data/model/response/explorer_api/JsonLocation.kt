package com.locationexplorer.data.model.response.explorer_api

import com.locationexplorer.data.model.database.Location
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JsonLocation(
    @Json(name = "address")
    val address: String?,
    @Json(name = "cc")
    val cc: String?,
    @Json(name = "city")
    val city: String?,
    @Json(name = "country")
    val country: String?,
    @Json(name = "crossStreet")
    val crossStreet: String?,
    @Json(name = "distance")
    val distance: Int?,
    @Json(name = "formattedAddress")
    val formattedAddress: List<String>?,
    @Json(name = "lat")
    val lat: Double?,
    @Json(name = "lng")
    val lng: Double?,
    @Json(name = "neighborhood")
    val neighborhood: String?,
    @Json(name = "postalCode")
    val postalCode: String?,
    @Json(name = "state")
    val state: String?
) {
    fun toLocation(): Location = Location(
        "",
        address ?: "",
        cc ?: "",
        city ?: "",
        country ?: "",
        crossStreet ?: "",
        distance ?: 0,
        formattedAddress ?: emptyList(),
        lat ?: 0.0,
        lng ?: 0.0,
        neighborhood ?: "",
        postalCode ?: "",
        state ?: ""
    )
}

