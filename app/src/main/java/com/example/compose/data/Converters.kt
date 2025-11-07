package com.example.compose.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromList(tags: List<String>): String {
        return tags.joinToString(",")
    }

    @TypeConverter
    fun toList(tags: String): List<String> {
        return if (tags.isEmpty()) emptyList() else tags.split(",")
    }
}
