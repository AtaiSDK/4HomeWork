package com.example.a4homework.data.db

import androidx.room.*
import com.example.a4homework.data.model.NoteModel
import com.google.common.net.HttpHeaders.FROM

@Dao
interface NoteDao {
    @Query("SELECT * FROM notemodel")
    fun getAllNote():List<NoteModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(model: NoteModel)

    @Update
    fun upDateNote(model: NoteModel)

    @Delete
    fun deleteNote(model: NoteModel)

    @Query("SELECT * FROM notemodel order BY date DESC")
    fun sortByDate(): List<NoteModel>

    @Query("SELECT * FROM notemodel ")
    fun sortByElement(): List<NoteModel>
}