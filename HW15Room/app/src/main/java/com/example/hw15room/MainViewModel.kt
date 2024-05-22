package com.example.hw15room

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val dictionaryDao: DictionaryDao) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Input)
    val state = _state.asStateFlow()
    val inputText = MutableStateFlow("")
    val mostCountedWords: StateFlow<List<DictionaryItem>> = this.dictionaryDao.getMostCounted(WORD_ITEMS_LIMIT)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun addWordClick(){
        val word = inputText.value
        viewModelScope.launch {
            val selectedItems = dictionaryDao.select(word)
            if (selectedItems.isNotEmpty()) {
                dictionaryDao.updateCount(
                    word = selectedItems.first().word,
                    count = selectedItems.first().count + 1
                )
            } else {
                dictionaryDao.insert(DictionaryItem(word))
            }
        }
        _state.value = State.Input
        inputText.value = ""
    }

    fun clearDictionary() {
        viewModelScope.launch { dictionaryDao.clear() }
    }

    fun inputEditTextChange() {
        inputText.debounce(DEBOUNCE_TIMEOUT_MILLIS).onEach {
            Log.d("ksvlog", "txt: $it")
            _state.value = State.Input
            if (it.matches(INPUT_REGEX)) {
                _state.value = State.Normal
                Log.d("ksvlog", "st: ${state.value}")
            } else {
                _state.value = State.Error
                Log.d("ksvlog", "st: ${state.value}")
            }
        }.launchIn(viewModelScope)
    }

    fun wordsToString(dictionaryItems: List<DictionaryItem>): String {
        val stringBuilder = StringBuilder()
        for (word in dictionaryItems) {
            if (word.count > 1)
                stringBuilder.append("${word.word} (${word.count})\n")
            else
                stringBuilder.append("${word.word}\n")
        }
        return stringBuilder.toString()
    }

    companion object {
        private const val WORD_ITEMS_LIMIT = 5
        private const val DEBOUNCE_TIMEOUT_MILLIS = 300L
        private val INPUT_REGEX = Regex("""[A-Z]?[a-z]+-?[a-z]+""")
    }
}