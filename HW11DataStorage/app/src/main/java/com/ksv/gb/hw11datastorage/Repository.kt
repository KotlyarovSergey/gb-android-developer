package com.ksv.gb.hw11datastorage

import android.content.Context
import android.content.Context.MODE_PRIVATE

private const val PREFERENCE_NAME = "preference_name"
private const val SHARED_PREFS_KEY = "shared_prefs_key"

class Repository(context: Context) {
    private var localValue: String? = null
    private val prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)

    //— будет доставать значение из SharedPreference;
    private fun getDataFromSharedPreference(): String? {
        return prefs.getString(SHARED_PREFS_KEY, null)
    }

    //— будет доставать значение, возвращать значение локальной переменной;
    private fun getDataFromLocalVariable(): String? {
        return localValue
    }

    //— будет записывать значения и в SharedPreference, и в локальную переменную.
    fun saveText(text: String) {
        saveDataToSharedPreference(text)
        saveDataToLocalVariable(text)
    }

    private fun saveDataToSharedPreference(text: String) {
        val editor = prefs.edit()
        editor.putString(SHARED_PREFS_KEY, text)
//        editor.putString(SHARED_PREFS_KEY, "$text from prefs")
        editor.apply()
    }
    private fun saveDataToLocalVariable(text: String) {
        localValue = text
//        localValue = "$text from local"
    }

    //— будет очищать значение и в SharedPreference, и в локальной переменной.
    fun clearText() {
        localValue = null
        val editor = prefs.edit()
        editor.remove(SHARED_PREFS_KEY)
        editor.apply()
    }

    // — будет доставать значение из источников: сначала попытается взять значение локальной переменной;
    // если оно null, то попытаемся взять значение из SharedPreference.
    fun getText(): String {
        return when {
            getDataFromLocalVariable() != null -> getDataFromLocalVariable()!!
            getDataFromSharedPreference() != null -> getDataFromSharedPreference()!!
            else -> ""
        }
    }
}