package com.ksv.gb.lesson1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ksv.gb.lesson1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.testTextView.text = binding.testTextView.context.getText(R.string.testText)

        binding.buttonClickMe.setOnClickListener{
            binding.plusButton.isEnabled = false
        }

        binding.plusButton.setOnClickListener {
            counter++
            if(counter == 6) {
                counter = 0
                binding.buttonClickMe.visibility = View.VISIBLE
            }
            binding.testTextView.text = counter.toString()
        }
    }
}