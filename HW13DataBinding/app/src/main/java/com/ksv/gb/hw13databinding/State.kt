package com.ksv.gb.hw13databinding

sealed class State(
    open val resultText: String? = null
){
    data class Normal(override val resultText: String?) : State(resultText = resultText)
    data object Search : State()
    data class Insufficient(override val resultText: String?) : State(resultText = resultText)
}
