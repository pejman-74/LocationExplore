package com.locationexplorer.data.model.database


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Venue(
    @PrimaryKey
    val id: String,
    val name: String,
)