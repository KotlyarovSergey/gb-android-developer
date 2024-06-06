package com.ksv.hw18permission.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ksv.hw18permission.entity.PhotoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoItemDao {
    @Query("SELECT * FROM photos")
    fun getAll(): Flow<List<PhotoItem>>

    @Insert
    suspend fun insert(photoItem: PhotoItem)

    @Delete
    suspend fun delete(photoItem: PhotoItem)
}