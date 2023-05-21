package com.example.a4homework.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a4homework.data.model.NoteModel


@Database(entities = [NoteModel::class], version = 1)
        abstract class NoteDatabase : RoomDatabase() {

            abstract fun getNoteDao(): NoteDao

            companion object{
                private var INSTANCE: NoteDatabase?=null
                private val LOCK = Any()

                operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
                    INSTANCE?: buildDataBAse(context).also {noteDatabase ->
                        INSTANCE = noteDatabase
                    }
                }
                private fun buildDataBAse(context: Context) =
                    Room.databaseBuilder(context,
                        NoteDatabase::class.java,
                        "DB Name").allowMainThreadQueries().build()
            }


}