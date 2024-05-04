package com.ksv.gb.hw13databinding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Insufficient)
    val state = _state.asStateFlow()

    private val _result = Channel<String?>()
    val result = _result.receiveAsFlow()

    var resultText: String = DEFAULT_RESULT_TEXT

    private var searched: String? = null


    //         TODO      удалить
    fun stateChange() {
        _state.value = when (_state.value) {
            State.Search -> State.Normal
            State.Normal -> State.Insufficient
            State.Insufficient -> State.Search
        }
    }


    fun searchEditChange(text: String) {
        if (text != searched) {
            viewModelScope.launch {
                if (text.length < MIN_SEARCH_LENGTH) {
                    _state.value = State.Insufficient
                    searched = null
                    resultText = DEFAULT_RESULT_TEXT
                } else {
                    _state.value = State.Normal
                    searched = text
                    resultText = getSearchResult(text)
                }
//                _result.send(null)
                _result.send(getSearchResult(text))
            }
        }
    }

//    fun onSearchClick(searchedText: String) {
//        viewModelScope.launch {
//            _state.value = State.Search
//            _result.send(null)
//            delay(5_000)
//            _result.send(getSearchResult(searchedText))
//            _state.value = State.Normal
//        }
//    }

    private fun getSearchResult(searchedText: String): String {
//        return "$SEARCH_RESULT_PREFIX$searchedText$SEARCH_RESULT_SUFFIX"
        return searchedText
    }

    companion object {
        const val MIN_SEARCH_LENGTH = 3
        const val DEFAULT_RESULT_TEXT = "Здесь будет отображаться результат запроса"
        const val SEARCH_RESULT_PREFIX = "По запросу \""
        const val SEARCH_RESULT_SUFFIX = "\" ничего не найдено"
    }
}