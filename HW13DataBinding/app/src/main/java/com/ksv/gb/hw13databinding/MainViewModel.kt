package com.ksv.gb.hw13databinding

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Insufficient(DEFAULT_RESULT_TEXT))
    val state = _state.asStateFlow()

//    private val _result = Channel<String?>()
//    val result = _result.receiveAsFlow()

//    var resultText: String = DEFAULT_RESULT_TEXT

//    private var searched: String? = null

    val searchedText = MutableStateFlow("")

    private var searchJob: Job? = null

    //         TODO      удалить
    fun stateChange() {
        _state.value = when (_state.value) {
            State.Search -> State.Normal(DEFAULT_RESULT_TEXT)
            is State.Normal -> State.Insufficient(DEFAULT_RESULT_TEXT)
            is State.Insufficient -> State.Search
        }
    }

    fun searchJobCancel(context: Context) {
        Log.d("ksvlog", "try to cancel Job")
        if (searchJob != null) {
            searchJob!!.cancel()
            Toast.makeText(context, "Job cancelled", Toast.LENGTH_SHORT).show()
            _state.value = State.Normal("Search cancel")
            Log.d("ksvlog", "Job cancelled")
        } else {

            Log.d("ksvlog", "Job is null")
        }
    }


    fun searchEditChange() {
        searchedText.debounce(DEBOUNCE_TIMEOUT_MILLIS).onEach {
            searchedTextChange(it)
            if (it.length < MIN_SEARCH_LENGTH) {
                _state.value = State.Insufficient(DEFAULT_RESULT_TEXT)
            } else {
                if (searchJob != null && searchJob!!.isActive) {
                    searchJob!!.cancel()
                }
//            searchJob?.cancel()
                doSearch()
            }
        }.launchIn(viewModelScope)

////        val text = searchedText.value
////        viewModelScope.launch {
//        if (searchedText.value.length < MIN_SEARCH_LENGTH) {
//            _state.value = State.Insufficient(DEFAULT_RESULT_TEXT)
////                    searched = null
////                resultText = DEFAULT_RESULT_TEXT
//        } else {
////                    searched = text
////                resultText = getSearchResult(text)
////                val resultText = getSearchResult()
////                _state.value = State.Normal(resultText)
//
//
//            if (searchJob != null && searchJob!!.isActive) {
//                searchJob!!.cancel()
////                Log.d("ksvlog", "Job cancelled")
//                Log.d("ksvlog", "Job cancelled ${searchJob.hashCode()} ${searchJob.toString()}")
//            }
////            searchJob?.cancel()
//            doSearch()
//        }
////                _result.send(getSearchResult(text))
////        }

    }

    private fun searchedTextChange(text: String) {
        _state.value = State.Normal(text)


    }


//    fun searchEditChange(text: String) {
//        if (text != searched) {
//            viewModelScope.launch {
//                if (text.length < MIN_SEARCH_LENGTH) {
//                    _state.value = State.Insufficient(DEFAULT_RESULT_TEXT)
//                    searched = null
//                    resultText = DEFAULT_RESULT_TEXT
//                } else {
//                    searched = text
//                    resultText = getSearchResult(text)
//                    _state.value = State.Normal(resultText)
//                }
//                _result.send(getSearchResult(text))
//            }
//        }
//    }

//    fun onSearchClick(searchedText: String) {
//        viewModelScope.launch {
//            _state.value = State.Search
//            _result.send(null)
//            delay(5_000)
//            _result.send(getSearchResult(searchedText))
//            _state.value = State.Normal
//        }
//    }

    //    private fun getSearchResult(searchedText: String): String {
    private fun getSearchResult(): String {
        viewModelScope.launch {
            _state.value = State.Search
//            _result.send(null)
            delay(SEARCH_DELAY_MILLiS)
//            _result.send(getSearchResult(searchedText))
//            _state.value = State.Normal
        }
//        return "$SEARCH_RESULT_PREFIX${searchedText.value}$SEARCH_RESULT_SUFFIX"
//        return searchedText
        return ""
    }

    private fun doSearch() {
        searchJob = viewModelScope.launch {
            Log.d("ksvlog", "Job start ${searchJob.hashCode()} ${searchJob.toString()}")
            _state.value = State.Search
            delay(SEARCH_DELAY_MILLiS)
            _state.value =
                State.Normal("$SEARCH_RESULT_PREFIX${searchedText.value}$SEARCH_RESULT_SUFFIX")

            Log.d("ksvlog", "Job finish ${searchJob.hashCode()} ${searchJob.toString()}")
        }
    }

    companion object {
        const val MIN_SEARCH_LENGTH = 3
        const val DEBOUNCE_TIMEOUT_MILLIS = 1000L
        const val SEARCH_DELAY_MILLiS = 3000L
        const val DEFAULT_RESULT_TEXT = "Здесь будет отображаться результат запроса"
        const val SEARCH_RESULT_PREFIX = "По запросу \""
        const val SEARCH_RESULT_SUFFIX = "\" ничего не найдено"
    }
}