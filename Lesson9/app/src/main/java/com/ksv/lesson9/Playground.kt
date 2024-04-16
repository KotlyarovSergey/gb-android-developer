package com.ksv.lesson9

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone

fun main(){
    val calend = Calendar.getInstance()
    println(calend.timeInMillis)

    calend.set(Calendar.YEAR, 2032)

    val day = calend.get(Calendar.DAY_OF_MONTH)
    val month = calend.get(Calendar.MONTH) + 1
    val year = calend.get(Calendar.YEAR)
    println("$day/$month/$year")

    val dateFormat = SimpleDateFormat("dd-MM-yy hh:mm")
    val date = calend.time
    val formater = dateFormat.format(date)
    println(formater)

    dateFormat.timeZone = TimeZone.getTimeZone("Europe/Madrid")
    val formatterDateMadrid = dateFormat.format(calend.time)
    println("Дата в Мадриде $formatterDateMadrid")

    dateFormat.timeZone = TimeZone.getTimeZone("GTM")
    val formatterDateGTM = dateFormat.format(calend.time)
    println("Дата по Гринвичу $formatterDateGTM")
}