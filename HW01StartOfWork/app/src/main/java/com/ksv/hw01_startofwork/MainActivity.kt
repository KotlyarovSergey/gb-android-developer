package com.ksv.hw01_startofwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ksv.hw01_startofwork.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}