package com.locationexplorer.util.network

interface ConnectionChecker {
    fun isConnectionAvailable(): Boolean
}