package com.ksv.gb.hw13databinding

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

    val searchedText = MutableStateFlow("")
    private var searchJob: Job? = null


    fun searchEditTextChange() {
        searchedText.debounce(DEBOUNCE_TIMEOUT_MILLIS).onEach {
            _state.value = State.Normal(it)
            if (it.length < MIN_SEARCH_LENGTH) {
                _state.value = State.Insufficient(DEFAULT_RESULT_TEXT)
            } else {
                if (searchJob != null && searchJob!!.isActive)
                    searchJob!!.cancel()

                doSearch()
            }
        }.launchIn(viewModelScope)
    }


    private fun doSearch() {
        searchJob = viewModelScope.launch {
            _state.value = State.Search
            delay(SEARCH_DELAY_MILLIS)
            _state.value =
                State.Normal("$SEARCH_RESULT_PREFIX${searchedText.value}$SEARCH_RESULT_SUFFIX")
        }
    }

    companion object{
        const val MIN_SEARCH_LENGTH = 3
        const val DEBOUNCE_TIMEOUT_MILLIS = 300L
        const val SEARCH_DELAY_MILLIS = 3000L
        const val DEFAULT_RESULT_TEXT = "Здесь будет отображаться результат запроса"
        const val SEARCH_RESULT_PREFIX = "По запросу \""
        const val SEARCH_RESULT_SUFFIX = "\" ничего не найдено"

    }
}