package com.locationexplorer.data.api

import com.locationexplorer.API_VERSION
import com.locationexplorer.BuildConfig
import com.locationexplorer.DEFAULT_SECTION
import com.locationexplorer.PAGE_SIZE
import com.locationexplorer.data.model.response.ExploreResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface ExploreApi {
    @GET("venues/explore")
    @FormUrlEncoded
    suspend fun explore(
        @Field("ll") ll: String,
        @Field("offset") offset: Int,
        @Field("section") section: String = DEFAULT_SECTION,
        @Field("limit") limit: Int = PAGE_SIZE,
        @Field("client_id") clientId: String = BuildConfig.CLIENT_ID,
        @Field("client_secret") clientSecret: String = BuildConfig.CLINET_SECRET,
        @Field("v") apiVersion: Int = API_VERSION,
    ): ExploreResponse
}
