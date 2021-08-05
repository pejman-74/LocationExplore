package com.locationexplorer.util.ext

import com.locationexplorer.data.model.share.SimpleLocation

val emptyLocation = SimpleLocation(0.0, 0.0)
fun SimpleLocation.isEmptyLocation(): Boolean = lat + long == 0.0