package com.ksv.gb.hw12mvvm

sealed class State{
    object Normal: State()
    object Search : State()
    object Insufficient: State()
}
