package com.example.hw15room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val wordDao: WordDao) : ViewModel() {
    val allWords: StateFlow<List<Word>> = this.wordDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun addWord(word: Word) {
        viewModelScope.launch {
            wordDao.insert(word)
        }
    }

    fun update(word: Word) {
        viewModelScope.launch {
            wordDao.updateCount(word.word, word.count)
        }
    }

    fun deleteLast(){
        viewModelScope.launch {
            allWords.value.lastOrNull()?.let{
                wordDao.delete(it)
            }
        }
    }
}