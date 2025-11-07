package com.example.compose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    // ✅ Insert or update note (Room replaces if same id)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NotesModel)

    // ✅ Insert or update note (Room replaces if same id)
    @Update()
    suspend fun update(note: NotesModel)


    // ✅ Optional: batch insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notes: List<NotesModel>)

    // ✅ Delete a note
    @Delete
    suspend fun delete(note: NotesModel)

    // ✅ Get all notes (returns a list)
    @Query("SELECT * FROM notes ORDER BY createdAt DESC")
     fun getAll(): Flow<List<NotesModel>>

    // ✅ Get note by id
    @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
    suspend fun getWithId(id: String): NotesModel?

    // ✅ Optional: search by tag or keyword
    @Query("SELECT * FROM notes WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
     fun searchNotes(query: String): Flow<List<NotesModel>>

    // ✅ Optional: delete all notes
    @Query("DELETE FROM notes")
    suspend fun deleteAll()

    @Query("DELETE FROM notes WHERE id IN (:ids)")
    suspend fun deleteNotesByIds(ids: List<String>)


}
