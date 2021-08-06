package com.locationexplorer.data.api

import com.locationexplorer.API_VERSION
import com.locationexplorer.BuildConfig
import com.locationexplorer.DEFAULT_SECTION
import com.locationexplorer.PAGE_SIZE
import com.locationexplorer.data.model.response.ExploreResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface ExploreApi {
    @GET("venues/explore")
    suspend fun explore(
        @Query("ll") ll: String,
        @Query("offset") offset: Int,
        @Query("section") section: String = DEFAULT_SECTION,
        @Query("limit") limit: Int = PAGE_SIZE,
        @Query("client_id") clientId: String = BuildConfig.CLIENT_ID,
        @Query("client_secret") clientSecret: String = BuildConfig.CLINET_SECRET,
        @Query("v") apiVersion: Int = API_VERSION,
    ): ExploreResponse
}
