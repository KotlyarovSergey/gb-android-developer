package com.example.hw15room

sealed class State(
//    open val inputError: Boolean = false,
    open val errorText: String? = null
) {
    data object Normal : State()
    data object TooShort : State()
    class Error(override val errorText: String?): State(errorText=errorText)

}