package com.locationexplorer.util.network

import com.locationexplorer.util.exception.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(private val connectionChecker: ConnectionChecker) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
       return if (connectionChecker.isConnectionAvailable())
            chain.proceed(chain.request())
        else
            throw NoInternetException()
    }
}