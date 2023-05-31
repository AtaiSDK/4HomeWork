package com.example.a4homework.data.db

import androidx.room.*
import com.example.a4homework.data.model.NoteModel

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


}