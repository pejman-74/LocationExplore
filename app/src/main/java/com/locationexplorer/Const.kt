package com.locationexplorer

const val API_VERSION = 20180323
const val PAGE_SIZE = 20
const val DEFAULT_SECTION = "topPicks"
const val BASE_URL = "https://api.foursquare.com/v2/"
const val DATABASE_NAME = "LocationExplorerDB"
const val DATASTORE_NAME = "LocationExplorerDataStore"
const val DATA_REFRESH_RATE = 86400000//one day
const val MIN_DISTANCE_LOCATION_UPDATE_RATE = 100F//meter
const val LOCATION_UPDATE_RATE = 1000L //one second

//Route
const val EXPLORER_SCREEN_ROUTE = "ExplorerScreen"