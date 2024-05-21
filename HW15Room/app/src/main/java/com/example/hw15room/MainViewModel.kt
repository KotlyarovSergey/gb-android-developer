package com.example.hw15room

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
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


    val mostCountedWords: StateFlow<List<Word>> = this.wordDao.getMostCounted(WORDS_LIMIT)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    val selected = MutableStateFlow<List<Word>>(emptyList())

    fun addWord(text: String) {
        for (word in allWords.value){
            if(text == word.word){
                val incCount = word.count + 1
                viewModelScope.launch {
                    wordDao.updateCount(word.word, incCount)
                }
                return
            }
        }
        viewModelScope.launch {
            wordDao.insert(Word(text))
        }



//        viewModelScope.launch {
//            val lst = wordDao.selectWord(text)
//            Log.d("ksvlog", "$lst")
//            if(lst.isEmpty()) {
//                val word = Word(text)
//                //try {
//                wordDao.insert(word)
//                //Log.d("ksvlog", "$word insert")
//                //} catch (ex: Exception){
//
//                //Log.d("ksvlog", "${ex.message}")
//                //}
//            }
//            else{
//                val cnt = lst.first().count + 1
//                wordDao.updateCount(text, cnt)
//            }
//
//        }
//
//
//
//        viewModelScope.launch {
////            val word = Word(text, 1)
//            val word = Word(text)
//            wordDao.insert(word)
//        }
    }

    fun findWord(word: String){
        viewModelScope.launch {
            //selected.value = wordDao.selectWord(word)
            Log.d("ksvlog", "${selected.value}")
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



    companion object{
        private val WORDS_LIMIT = 5
    }
}