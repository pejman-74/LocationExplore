package com.locationexplorer.data.api

import com.firstPageJson
import com.locationexplorer.data.model.response.explorer_api.ExploreResponse
import com.squareup.moshi.Moshi
import java.io.IOException


class FakeExploreApi(private val moshi: Moshi) : ExploreApi {

    var shouldThrowIoException = false
    var currentJson = firstPageJson

    override suspend fun explore(
        ll: String,
        offset: Int,
        section: String,
        limit: Int,
        clientId: String,
        clientSecret: String,
        apiVersion: Int
    ): ExploreResponse {
        if (shouldThrowIoException)
            throw IOException("This is test io exception")

        return runCatching {
            moshi.adapter(ExploreResponse::class.java).fromJson(currentJson)
        }.getOrNull() ?: throw IOException("Cannot convert from json")

    }
}
