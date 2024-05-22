package com.example.hw15room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDao {
//    @Query("SELECT * FROM words")
//    fun getAll(): Flow<List<Word>>

    @Query("SELECT * FROM words ORDER BY count DESC LIMIT :limit")
    fun getMostCounted(limit: Int): Flow<List<DictionaryItem>>

    @Query("SELECT * FROM words WHERE word=:word")
    suspend fun select(word: String): List<DictionaryItem>

    @Insert
    suspend fun insert(dictionaryItem: DictionaryItem)

    @Query("UPDATE words SET count=:count WHERE word=:word")
    suspend fun updateCount(word: String, count: Int)

    @Query("DELETE FROM words")
    suspend fun clear()

}