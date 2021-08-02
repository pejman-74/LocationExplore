package com.locationexplorer.data.wapper


sealed class Resource<T>(
    open val data: T? = null,
    open val throwable: Throwable? = null
) {
    class Empty<T> : Resource<T>()
    class Success<T>(override val data: T) : Resource<T>(data)
    class Loading<T>(override val data: T? = null) : Resource<T>(data)
    class Error<T>(override val data: T? = null, override val throwable: Throwable) :
        Resource<T>(data, throwable)
}
