package com.locationexplorer.data.wapper

import com.locationexplorer.data.model.share.SimpleLocation

sealed class LocationObserverStates {
    class LocationChange(val simpleLocation: SimpleLocation) : LocationObserverStates()
    object ProviderDisabled : LocationObserverStates()
    object ProviderEnabled : LocationObserverStates()
}