package com.ksv.gb.hw10lifecycle

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

var tick = true
var count = 5

fun main() = runBlocking{
    println("start program")
    launch {
        longTimeFunction()
    }
    println("stop program")

}

suspend fun longTimeFunction(){
    println("start long timeFunc")
    while(count > 0){
        if(tick)
            println("tick")
        else
            println("tack")
        tick = !tick
        delay(1000L)
        count--
    }
    println("stop long timeFunc")
}