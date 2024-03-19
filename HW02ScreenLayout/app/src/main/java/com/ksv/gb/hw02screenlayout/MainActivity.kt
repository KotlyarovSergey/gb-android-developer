package com.ksv.gb.hw02screenlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ksv.gb.hw02screenlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding.customPanel){
            bottomText.text = getString(R.string.bottom_text)
            topText.text= getString(R.string.top_text)
        }
    }
}