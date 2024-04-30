package com.ksv.gb.hw13databinding

sealed class State{
    object Normal: State()
    object Search : State()
    object Insufficient: State()
}
