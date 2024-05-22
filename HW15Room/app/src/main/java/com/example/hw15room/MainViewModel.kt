package com.example.hw15room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val dictionaryDao: DictionaryDao) : ViewModel() {
//    val allWords: StateFlow<List<Word>> = this.wordDao.getAll()
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000L),
//            initialValue = emptyList()
//        )


    val mostCountedWords: StateFlow<List<DictionaryItem>> = this.dictionaryDao.getMostCounted(WORD_ITEMS_LIMIT)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun addWord(word: String) {
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
    }

    fun clearDictionary() {
        viewModelScope.launch { dictionaryDao.clear() }
    }

    companion object {
        private const val WORD_ITEMS_LIMIT = 5
    }
}