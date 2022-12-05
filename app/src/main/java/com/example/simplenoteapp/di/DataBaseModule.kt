package com.example.simplenoteapp.di

import android.content.Context
import androidx.room.Room
import com.example.simplenoteapp.common.Constants.NOTE_DB_NAME
import com.example.simplenoteapp.db.NoteDao
import com.example.simplenoteapp.db.NoteDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NoteDataBase {
        return Room.databaseBuilder(
            context,
            NoteDataBase::class.java,
            NOTE_DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun noteDao(dataBase: NoteDataBase): NoteDao {
        return dataBase.getNoteDao()
    }
}