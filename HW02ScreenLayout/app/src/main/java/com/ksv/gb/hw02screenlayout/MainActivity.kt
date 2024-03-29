package com.ksv.gb.hw02screenlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ksv.gb.hw02screenlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.customPanel.setTopText(getString(R.string.top_text))
        binding.customPanel.setBottomText(getString(R.string.bottom_text))
    }
}