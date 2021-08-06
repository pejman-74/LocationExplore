package com.locationexplorer.util.ext

import com.locationexplorer.data.wapper.Resource

suspend inline fun <T : Any> simpleSafeApiCall(
    crossinline api: suspend () -> T
): Resource<T> {
    return try {
        val response = api.invoke()
        Resource.Success(response)
    } catch (exception: Exception) {
        Resource.Error(throwable = exception)
    }
}