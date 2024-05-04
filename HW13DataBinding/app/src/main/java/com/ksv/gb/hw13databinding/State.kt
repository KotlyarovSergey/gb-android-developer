package com.ksv.gb.hw13databinding

sealed class State{
    data object Normal : State()
    data object Search : State()
    data object Insufficient: State()
}
