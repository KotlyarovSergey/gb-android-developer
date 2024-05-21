package com.example.hw15room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM words")
    fun getAll(): Flow<List<Word>>

    @Insert
    suspend fun insert(word: Word)

    @Query("INSERT INTO words VALUES (:word, :count)")
    suspend fun insertFull(word: String, count: Int)

    @Delete
    suspend fun delete(word: Word)

    @Query("DELETE FROM words WHERE word=:word")
    suspend fun deleteWord(word: String)

    @Query("UPDATE words SET count = :count WHERE word=:word")
    suspend fun updateCount(word: String, count: Int)

}