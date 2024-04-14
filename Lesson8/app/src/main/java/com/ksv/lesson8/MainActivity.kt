package com.ksv.lesson8

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.buttonActivityB)
        val option = ActivityOptions.makeSceneTransitionAnimation(this)

        button.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ActivityB::class.java
                ),
                option.toBundle()
            )
        }
    }
}