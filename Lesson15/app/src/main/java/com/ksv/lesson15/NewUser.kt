package com.ksv.lesson15

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class NewUser(
    @PrimaryKey
    @ColumnInfo(name="id")
    val id: Int? = null,
    @ColumnInfo(name="first_name")
    val firstName: String,
    @ColumnInfo(name="last_name")
    val lastName: String,
    @ColumnInfo(name="age")
    val age: Int
)
