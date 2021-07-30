package com.locationexplorer.util.network

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class ConnectionCheckerImpl(context: Context) : ConnectionChecker {

    private val cManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun isConnectionAvailable(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = cManager.activeNetwork ?: return false
            val networkCapabilities =
                cManager.getNetworkCapabilities(network) ?: return false
            when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            cManager.activeNetworkInfo?.let {
                when (it.type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            } ?: false
        }

    }
}