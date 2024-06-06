package com.ksv.hw18permission.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ksv.hw18permission.entity.PhotoItem

@Database(entities =[PhotoItem::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun photoDao(): PhotoItemDao
}