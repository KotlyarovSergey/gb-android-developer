package com.example.hw15room

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val wordDao: WordDao) : ViewModel() {
//    val allWords: StateFlow<List<Word>> = this.wordDao.getAll()
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000L),
//            initialValue = emptyList()
//        )


    val mostCountedWords: StateFlow<List<Word>> = this.wordDao.getMostCounted(WORDS_LIMIT)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    //val selected = MutableStateFlow<List<Word>>(emptyList())

    fun addWord(text: String) {
        // это работает
//        for (word in allWords.value){
//            if(text == word.word){
//                val incCount = word.count + 1
//                viewModelScope.launch {
//                    wordDao.updateCount(word.word, incCount)
//                }
//                return
//            }
//        }
//        viewModelScope.launch {
//            wordDao.insert(Word(text))
//        }


        viewModelScope.launch {
            val words = wordDao.select(text)
            if (words.isNotEmpty()) {
                wordDao.updateCount(
                    word = words.first().word,
                    count = words.first().count + 1
                )
            } else {
                wordDao.insert(Word(text))
            }
        }

    }

    fun clear() {
        viewModelScope.launch { wordDao.clear() }
    }


//    fun findWord(word: String){
//        viewModelScope.launch {
//            selected.value = wordDao.select(word)
//            Log.d("ksvlog", "${selected.value}")
//        }
//    }

//    fun update(word: Word) {
//        viewModelScope.launch {
//            wordDao.updateCount(word.word, word.count)
//        }
//    }

//    fun deleteLast(){
//        viewModelScope.launch {
//            allWords.value.lastOrNull()?.let{
//                wordDao.delete(it)
//            }
//        }
//    }

//    fun deleteByWord(word: String){
//        viewModelScope.launch{
////            wordDao.delete(Word(word))
//        }
//    }


    companion object {
        private const val WORDS_LIMIT = 5
    }
}