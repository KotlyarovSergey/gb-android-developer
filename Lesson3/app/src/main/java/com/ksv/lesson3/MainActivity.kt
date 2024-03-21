package com.ksv.lesson3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var longText = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bnt = findViewById<Button>(R.id.btnChangeText)
        val tv = findViewById<TextView>(R.id.tvDecription)
        bnt.setOnClickListener {
            longText =! longText

            tv.text = if(longText) getString(R.string.long_text)
            else getString(R.string.short_text)
        }
    }
}