package com.ksv.gb.lesson11

import android.content.Context
import android.content.Context.MODE_PRIVATE
import java.io.FileInputStream
import java.io.IOException

private const val PREFERENCE_NAME = "preference_name"
private const val SHARED_PREFS_KEY = "shared_prefs_key"
private const val FILE_NAME = "fileName.txt"

class Repository {

    fun loadText(context: Context): String{
        return when {
            getFromLocalVariable() != null -> getFromLocalVariable()!!
            loadFromPreference(context) != null -> loadFromPreference(context)!!
            loadFromFile(context) != null -> loadFromFile(context)!!
            else -> "no one source doesn't contain string"
        }
    }

    private fun loadFromPreference(context: Context): String? {
//        val prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
//        return prefs.getString(SHARED_PREFS_KEY, null)
//
        return null
    }

    fun saveToPreferences(context: Context, savedText: String){
        val prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(SHARED_PREFS_KEY, savedText)
        editor.apply()
    }

    private fun loadFromFile(context: Context): String? {
        var fin: FileInputStream? = null
        return try{
            fin = context.openFileInput(FILE_NAME)
            val bytes = ByteArray(fin.available())
            fin.read(bytes)
            String(bytes)
        } catch (ex: IOException){
            null
        } finally {
            fin?.close()
        }
    }

    private fun getFromLocalVariable(): String? {
//        return "from local val"
        return null
    }
}