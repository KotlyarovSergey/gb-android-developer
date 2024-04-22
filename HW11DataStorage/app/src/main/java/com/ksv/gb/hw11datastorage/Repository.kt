package com.ksv.gb.hw11datastorage

class Repository {
    private var localValue: String? = null
    //— будет доставать значение из SharedPreference;
    private fun getDataFromSharedPreference(): String? {
        return null
    }

    //— будет доставать значение, возвращать значение локальной переменной;
    private fun getDataFromLocalVariable(): String? {
        return null
    }

    //— будет записывать значения и в SharedPreference, и в локальную переменную.
    fun saveText(text: String) {

    }

    //— будет очищать значение и в SharedPreference, и в локальной переменной.
    fun clearText() {

    }

    // — будет доставать значение из источников: сначала попытается взять значение локальной переменной;
    // если оно null, то попытаемся взять значение из SharedPreference.
    fun getText(): String {
        return "empty"
    }
}