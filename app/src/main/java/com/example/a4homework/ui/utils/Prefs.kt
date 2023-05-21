package com.example.a4homework.ui.utils

import android.content.SharedPreferences

class Prefs(private val preferences: SharedPreferences) {
    fun safeBoardState(){
        preferences.edit().putBoolean("isShow", true).apply()
    }
    fun isBoardShow(): Boolean{
        return  preferences.getBoolean("isShow", false)
    }
}