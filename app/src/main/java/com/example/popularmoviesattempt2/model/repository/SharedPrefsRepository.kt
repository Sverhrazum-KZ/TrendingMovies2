package com.example.popularmoviesattempt2.model.repository

interface SharedPrefsRepository {

    fun saveInPrefs(value:String):String

}