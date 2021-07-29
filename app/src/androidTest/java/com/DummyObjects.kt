package com

import com.locationexplorer.data.model.response.Location
import com.locationexplorer.data.model.response.Venue

val location =
    Location(
        "1", "", "IR", "Tehran", "ایران",
        "", 2312, listOf(
            "Tehran, Tehran Province",
            "ایران"
        ), 35.709976, 51.315655,
        "", "", "Tehran"
    )
val venue = Venue("1", "coffeeShop")