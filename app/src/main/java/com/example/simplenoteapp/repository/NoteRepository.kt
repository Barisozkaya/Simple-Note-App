package com.example.simplenoteapp.repository

import com.example.simplenoteapp.db.NoteDataBase
import com.example.simplenoteapp.model.Note
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class NoteRepository @Inject constructor(private val db: NoteDataBase) {

    suspend fun addNote(note: Note) = db.getNoteDao().addNote(note)

    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)

    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)

    fun getAllNotes() = db.getNoteDao().getAllNotes()

    fun searchNotes(query: String?) = db.getNoteDao().searchNote(query)
}