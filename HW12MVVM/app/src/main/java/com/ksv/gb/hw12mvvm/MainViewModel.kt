package com.ksv.gb.hw12mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _state = MutableStateFlow<State>(State.Insufficient)
    val state = _state.asStateFlow()

    private val _result = Channel<String?>()
    val result = _result.receiveAsFlow()

    private var searched: String? = null

    fun searchEditChange(text: String){
        if(text != searched) {
            viewModelScope.launch {
                if (text.length < MIN_SEARCH_LENGTH) {
                    _state.value = State.Insufficient
                    searched = null
                } else {
                    _state.value = State.Normal
                    searched = text
                }
                _result.send(null)
            }
        }
    }

    fun onSearchClick(searchedText: String) {
        viewModelScope.launch {
            _state.value = State.Search
            _result.send(null)
            delay(5_000)
            _result.send(getSearchResult(searchedText))
            _state.value = State.Normal
        }
    }

    private fun getSearchResult(searchedText: String) : String{
        return searchedText
    }

    companion object{
        const val MIN_SEARCH_LENGTH = 3
    }
}