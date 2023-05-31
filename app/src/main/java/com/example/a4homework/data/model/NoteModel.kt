package com.example.a4homework.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    val title: String? = null,
    val desc: String? = null,
    val date: String? = null
)
