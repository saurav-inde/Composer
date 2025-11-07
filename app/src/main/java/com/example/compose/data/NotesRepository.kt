package com.example.compose.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepository @Inject constructor(private val dao: NotesDao) {

    suspend fun insert(note: NotesModel) = dao.insert(note)

    suspend fun update(note: NotesModel) = dao.update(note)

    suspend fun delete(note: NotesModel) = dao.delete(note)

     fun getAll(): Flow<List<NotesModel>> = dao.getAll()

    suspend fun getWithId(id: String): NotesModel? = dao.getWithId(id)

    suspend fun search(query: String): Flow<List<NotesModel>> = dao.searchNotes(query)


    suspend fun deleteNotesByIds(toList: List<String>) {
    dao.deleteNotesByIds(toList)
    }


}
