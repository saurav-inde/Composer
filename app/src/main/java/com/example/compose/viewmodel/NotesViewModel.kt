package com.example.compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.data.NotesModel
import com.example.compose.data.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {
    private val _selectedNotes = MutableStateFlow<Set<String>>(emptySet())
    val selectedNotes = _selectedNotes.asStateFlow()
    val notes: Flow<List<NotesModel>> = repository.getAll()
    fun deleteSelected() {
        viewModelScope.launch {
            repository.deleteNotesByIds(_selectedNotes.value.toList())
            clearSelection()
        }
    }
    fun toggleSelection(noteId: String) {
        _selectedNotes.value = if (_selectedNotes.value.contains(noteId))
            _selectedNotes.value - noteId
        else
            _selectedNotes.value + noteId
    }
    fun clearSelection() {
        _selectedNotes.value = emptySet()
    }

    fun addNotes(note : NotesModel) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    fun deleteNote(note : NotesModel) {
        viewModelScope.launch {
            repository.delete(note)
        }
    }

}