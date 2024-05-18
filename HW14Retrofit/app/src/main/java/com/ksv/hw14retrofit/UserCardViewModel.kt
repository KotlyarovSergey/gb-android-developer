package com.ksv.hw14retrofit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserCardViewModel : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    private val _person = MutableStateFlow<Person?>(null)
    val person = _person.asStateFlow()

    private var firstLaunch = true

    fun getDataFromServer() {
        Log.d("ksvlog", "ViewModel: refreshData")
        viewModelScope.launch {
            _state.value = State.Loading
            val response = RetrofitInstance.getPersonApiResponse.getPersonData()
//            delay(1000L)  // имитация "задумчивости" сервера

            // имитация ошибки получения данных с сервера
//            val rnd = (0..10).random()
//            if(rnd == 0){
//                _state.value = State.Error
//            } else {
            if (response.isSuccessful) {
                _person.value = response.body()
                _state.value = State.Normal
            } else {
                _state.value = State.Error
                Log.d("ksvlog", "Person getting failure")
            }
//            }

        }
    }

    fun firstLaunch() {
        if (firstLaunch) {
            getDataFromServer()
            firstLaunch = false
        }
    }
}