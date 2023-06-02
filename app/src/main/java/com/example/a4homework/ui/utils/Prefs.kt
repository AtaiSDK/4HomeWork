package com.example.a4homework.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast

class Prefs(private val preferences: SharedPreferences) {
    fun safeBoardState(){
        preferences.edit().putBoolean("isShow", true).apply()
    }
    fun isBoardShow(): Boolean{
        return  preferences.getBoolean("isShow", false)
    }
    @SuppressLint("CommitPrefEdits")
    fun logOut(context: Context){
        preferences.edit().clear().apply()
        Toast.makeText(context, "Successfully logged out", Toast.LENGTH_SHORT).show()
    }
    fun saveUser(){
        preferences.edit().putBoolean("registered", true).apply()
    }
    fun isRegisterShowed(): Boolean{
        return  preferences.getBoolean("registered", false)
    }
    @SuppressLint("CommitPrefEdits")
    fun saveUserName(name: String){
        preferences.edit().putString("name", name).apply()
    }
    fun getUserName():String{
        return  preferences.getString("name", "")!!
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
        return  preferences.getInt("number", 1)
    }

    fun isUserMarried(isMarried: Boolean){
        preferences.edit().putBoolean("isMarried", isMarried).apply()
    }

    fun getUserMarried(): Boolean {
        return preferences.getBoolean("isMarried", false)
    }

    fun saveUserImage(image: String){
        preferences.edit().putString("image", image).apply()
    }
    fun getUserImage():String{
        return  preferences.getString("image", "Unknown")!!
    }
}