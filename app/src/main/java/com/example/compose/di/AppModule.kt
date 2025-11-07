package com.example.compose.di

import android.content.Context
import androidx.room.Room
import com.example.compose.data.NotesDao
import com.example.compose.data.NotesDatabase
import com.example.compose.data.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : NotesDatabase =
          Room.databaseBuilder(
            appContext,
            NotesDatabase::class.java,
            "notes_db"
        ).build()


    @Provides
    @Singleton
    fun provideNotesDao(db : NotesDatabase):NotesDao = db.notesDao()


    @Provides
    @Singleton
    fun provideNotesRepository(dao : NotesDao) : NotesRepository = NotesRepository(dao)
}