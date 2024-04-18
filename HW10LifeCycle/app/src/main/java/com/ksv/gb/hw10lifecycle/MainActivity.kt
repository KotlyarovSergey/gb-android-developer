package com.ksv.gb.hw10lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ksv.gb.hw10lifecycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}