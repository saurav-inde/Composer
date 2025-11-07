package com.example.compose.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.android.identity.documenttype.DocumentAttributeType
import java.time.Instant
import java.util.UUID

@Entity(tableName = "notes")
@TypeConverters(Converters::class)
data class NotesModel(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),

    val title: String,
    val content: String,
    val tags: List<String> = emptyList(),

    val pinned: Boolean = false,
    val archived: Boolean = false,
    val deletedAt: Long? = null, // null → active, non-null → soft deleted

    val color: Int = 0xFFFFFFFF.toInt(), // white by default (ARGB int)

    val createdAt: Long = Instant.now().epochSecond,
    val updatedAt: Long = Instant.now().epochSecond,
    val version: Int = 1
)