package com.example.a4homework.ui

import android.app.Application
import android.content.SharedPreferences
import com.example.a4homework.data.db.NoteDatabase
import com.example.a4homework.ui.utils.Prefs

class App : Application() {
    private lateinit var preferences: SharedPreferences
    companion object {
        lateinit var prefs: Prefs
        lateinit var db: NoteDatabase
    }
    override fun onCreate() {
        super.onCreate()
        val preferences = this.applicationContext
            .getSharedPreferences("settings", MODE_PRIVATE)
        prefs = Prefs(preferences)
        db = NoteDatabase.invoke(this.applicationContext)
    }
}