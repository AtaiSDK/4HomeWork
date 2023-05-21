package com.example.a4homework.data.db

import androidx.room.*
import com.example.a4homework.data.model.NoteModel

@Dao
interface NoteDao {
    @Query("select * from notemodel")
    fun getAllNote():List<NoteModel>

    @Insert
    fun addNote(model: NoteModel)

    @Update
    fun upDateNote(model: NoteModel)

    @Delete
    fun deleteNote(model: NoteModel)


}