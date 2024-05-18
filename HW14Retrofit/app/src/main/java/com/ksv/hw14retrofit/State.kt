package com.ksv.hw14retrofit

sealed class State {
    data object Normal: State()
    data object Loading: State()
    data object Error: State()
}