package com.ksv.lesson15

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
//    @Query("SELECT * FROM users WHERE age < 5")
//    @Query("SELECT * FROM users LIMIT 5")
//    @Query("SELECT * FROM users WHERE first_name LIKE :name")
    fun getAll(): Flow<List<User>>
//    fun getAll(name: String): Flow<List<User>>

    @Insert(entity = User::class)
    suspend fun insert(user: NewUser)
    @Update
    suspend fun update(user: User)
    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM users WHERE first_name LIKE :name")
    suspend fun selectByName(name: String): List<User>

    @Query("UPDATE users SET first_name=:newName WHERE id=:id")
    suspend fun updateById(id: Int, newName: String)
}