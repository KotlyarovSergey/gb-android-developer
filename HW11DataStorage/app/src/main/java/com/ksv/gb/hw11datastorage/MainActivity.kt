package com.ksv.gb.hw11datastorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.ksv.gb.hw11datastorage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val repository = Repository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = repository.getText()

        binding.saveButton.setOnClickListener {
            val text = binding.inputText.text.toString()
            repository.saveText(text)
            binding.textView.text = text
        }
        binding.clearButton.setOnClickListener {
            repository.clearText()
            binding.inputText.text?.clear()
            binding.inputText.text?.clear()
        }

    }
}