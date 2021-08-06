package com.locationexplorer.data.model.response.venue_api


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Venue(
    @Json(name = "bestPhoto")
    val bestPhoto: BestPhoto?,
    @Json(name = "canonicalUrl")
    val canonicalUrl: String?,
    @Json(name = "categories")
    val categories: List<Category>?,
    @Json(name = "contact")
    val contact: Contact?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "likes")
    val likes: Likes?,
    @Json(name = "listed")
    val listed: Listed?,
    @Json(name = "location")
    val location: Location?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "shortUrl")
    val shortUrl: String?,
)