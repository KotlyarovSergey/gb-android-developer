package com.example.hw15room

sealed class State {
    data object Normal : State()
    data object Input : State()
    data object Error : State()
}