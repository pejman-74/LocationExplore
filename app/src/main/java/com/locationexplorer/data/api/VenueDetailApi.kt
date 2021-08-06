package com.locationexplorer.data.api

import com.locationexplorer.API_VERSION
import com.locationexplorer.BuildConfig
import com.locationexplorer.data.model.response.venue_api.VenueResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VenueDetailApi {

    @GET("venues/{venueId}")
    suspend fun venueDetail(
        @Path("venueId") venueId: String,
        @Query("client_id") clientId: String = BuildConfig.CLIENT_ID,
        @Query("client_secret") clientSecret: String = BuildConfig.CLINET_SECRET,
        @Query("v") apiVersion: Int = API_VERSION,
    ):VenueResponse
}