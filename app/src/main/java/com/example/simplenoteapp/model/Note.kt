package com.example.simplenoteapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simplenoteapp.common.Constants.NOTE_TABLE_NAME
import kotlinx.parcelize.Parcelize

@Entity(tableName = NOTE_TABLE_NAME)
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nateTitle: String,
    val noteBody: String
): Parcelable
