package com.example.simplenoteapp.repository

import com.example.simplenoteapp.db.NoteDataBase
import com.example.simplenoteapp.model.Note

class NoteRepository(private val db: NoteDataBase) {

    suspend fun addNote(note: Note) = db.getNoteDao().addNote(note)

    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)

    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)

    fun getAllNotes() = db.getNoteDao().getAllNotes()
}