package com.locationexplorer.util.ext

import com.locationexplorer.data.wapper.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalCoroutinesApi::class)
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: suspend (ResultType) -> Boolean = { true }
) = channelFlow {
    val data = query().first()

    if (shouldFetch(data)) {
        send(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().collect { send(Resource.Success(it)) }
        } catch (t: Throwable) {
            query().collect { send(Resource.Error(it, t)) }
        }
    } else {
        send(Resource.Success(data))
    }
}