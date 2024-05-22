package com.example.hw15room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities =[DictionaryItem::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun wordDao(): DictionaryDao
}