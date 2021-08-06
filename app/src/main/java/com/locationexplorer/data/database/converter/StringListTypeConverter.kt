package com.locationexplorer.data.database.converter

import androidx.room.TypeConverter

class StringListTypeConverter {
    @TypeConverter
    fun stringListToString(strings: List<String>): String = strings.joinToString("*")

    @TypeConverter
    fun stringToStringList(string: String): List<String> = string.split("*")
}