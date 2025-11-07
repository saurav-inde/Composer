package com.example.compose.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    version = 1,
    entities = [NotesModel::class],
    exportSchema = false
)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun notesDao() : NotesDao
}