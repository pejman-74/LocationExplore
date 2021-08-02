package com.locationexplorer.util.network

class FakeConnectionChecker : ConnectionChecker {
    var isHaveConnection = true
    override fun isConnectionAvailable(): Boolean = isHaveConnection
}