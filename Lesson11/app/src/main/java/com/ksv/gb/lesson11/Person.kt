package com.ksv.gb.lesson11

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val name: String = "Alex",
    val age: Int = 27
): Parcelable
