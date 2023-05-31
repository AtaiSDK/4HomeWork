package com.example.a4homework.ui.utils

import android.content.SharedPreferences

class Prefs(private val preferences: SharedPreferences) {
    fun safeBoardState(){
        preferences.edit().putBoolean("isShow", true).apply()
    }
    fun isBoardShow(): Boolean{
        return  preferences.getBoolean("isShow", false)
    }
    fun saveUserName(name: String){
        preferences.edit().putString("name", name).apply()
    }
    fun getUserName():String{
        return  preferences.getString("name", "Unknown")!!
    }

    fun saveUserSurname(name: String){
        preferences.edit().putString("surname", name).apply()
    }
    fun getUserSurName():String{
        return  preferences.getString("surname", "Unknown")!!
    }

    fun saveUserNumber(number : Int){
        preferences.edit().putInt("number", number).apply()
    }
    fun getUserNumber():Int{
        return  preferences.getInt("number", 1)!!
    }

    fun saveUserImage(Image: String){
        preferences.edit().putString("image", Image).apply()
    }
    fun getUserImage():String{
        return  preferences.getString("image", "Unknown")!!
    }
}