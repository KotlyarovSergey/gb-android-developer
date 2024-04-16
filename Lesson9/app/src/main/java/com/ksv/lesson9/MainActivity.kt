package com.ksv.lesson9

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.ksv.lesson9.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("dd-MM-yy HH:mm")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val planets = resources.getStringArray(R.array.planets)
        val fromM = planets.filter{ it.startsWith("М")}

        val intArray: IntArray = resources.getIntArray(R.array.olympic_years)

        var count = resources.getQuantityString(R.plurals.count_new_messages, 1, 1)
        Log.d("ksvlog", count)

        count = resources.getQuantityString(R.plurals.count_new_messages, 2, 2)
        Log.d("ksvlog", count)

        count = resources.getQuantityString(R.plurals.count_new_messages, 4, 4)
        Log.d("ksvlog", count)

        for (i in 0..10){
            val txt = resources.getQuantityString(R.plurals.money, 1, i)
            Log.d("ksvlog", txt)
        }
        Log.d("ksvlog", "------------------")

        for (i in 0..10){
            try {
                val txt = resources.getQuantityString(R.plurals.money, i, 1)
                Log.d("ksvlog", "$i: $txt")
            } catch (exception: Resources.NotFoundException){
                Log.d("ksvlog", exception.message.toString())
            }

        }



        binding.openDatePicker.setOnClickListener {
            val constraints = CalendarConstraints.Builder()
                .setOpenAt(calendar.timeInMillis)
                .build()

            val dateDialog = MaterialDatePicker.Builder.datePicker()
//                .setSelection(calendar.timeInMillis)
                .setCalendarConstraints(constraints)
                .setTitleText(getString(R.string.open_date_dialog_title))
                .build()

            dateDialog.addOnPositiveButtonClickListener { timeInMillis ->
                calendar.timeInMillis = timeInMillis
//                val day = calendar.get(Calendar.DAY_OF_MONTH)
//                val month = calendar.get(Calendar.MONTH) + 1
//                val year = calendar.get(Calendar.YEAR)
//                val text = "$day/$month/$year"

                Snackbar.make(binding.openTimePicker, dateFormat.format(calendar.time), Snackbar.LENGTH_SHORT).show()
            }

            dateDialog.show(supportFragmentManager, "DatePicker")
        }


        binding.openTimePicker.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setTitleText(getString(R.string.open_time_picker_title))
                .setHour(calendar.get(Calendar.HOUR_OF_DAY))
                .setMinute(calendar.get(Calendar.MINUTE))
                .build().apply {
                    addOnPositiveButtonClickListener {
                        calendar.set(Calendar.HOUR_OF_DAY, this.hour)
                        calendar.set(Calendar.MINUTE, this.minute)

//                        val time = "${calendar.get(Calendar.HOUR)}:${calendar.get(Calendar.MINUTE)}"
                        Snackbar.make(binding.openTimePicker, dateFormat.format(calendar.time), Snackbar.LENGTH_SHORT).show()
                    }
                }
            timePicker.show(supportFragmentManager, "TimePicker")
        }

    }
}