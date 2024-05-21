package com.example.hw15room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.hw15room.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val wordDao: WordDao = (application as App).db.wordDao()
                return MainViewModel(wordDao) as T
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.allWords
//                viewModel.mostCountedWords
                    .collect { words ->
                        binding.resultTV.text = words.joinToString("\n")
                    }
            }
        }

//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED){
//                viewModel.selected
//                    .collect {words ->
////                        binding.clearButton.text = words.count().toString()
//                        words.firstOrNull()?.let { word ->
//                            binding.clearButton.text = word.word
//                        }
//                    }
//            }
//        }

        binding.addButton.setOnClickListener{
            val txt = binding.inputEdit.text.toString()
            if(txt.isNotBlank()){
                viewModel.addWord(txt)
            }
        }

        binding.clearButton.setOnClickListener {
            viewModel.deleteLast()
            //viewModel.findWord(binding.inputEdit.text.toString())
        }


    }
}