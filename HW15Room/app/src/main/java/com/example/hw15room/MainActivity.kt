package com.example.hw15room

import android.health.connect.datatypes.units.Length
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.hw15room.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
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
//                viewModel.allWords
                viewModel.mostCountedWords
                    .collect { words ->
                        //binding.resultTV.text = words.joinToString("\n")
                        binding.resultTV.text = wordsToString(words)
                    }
            }
        }

        // это работает
        // collect для allWords
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED){
//                viewModel.allWords
////                viewModel.mostCountedWords
//                    .collect { words ->
//                        //binding.resultTV.text = words.joinToString("\n")
//                        Log.d("ksvlog", "${words.size}")
//                    }
//            }
//        }



//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED){
//                viewModel.selected
//                    .collect {words ->
////                        binding.clearButton.text = words.size.toString()
//                        words.firstOrNull()?.let { word ->
//                            binding.clearButton.text = word.word
//                        }
//                    }
//            }
//        }

        binding.addButton.setOnClickListener{
            val inputText = binding.inputEdit.text.toString()
            if (inputText.matches(INPUT_REGEX)) {
                val txt = binding.inputEdit.text.toString()
                if (txt.isNotBlank()) {
                    viewModel.addWord(txt)
                    binding.inputEdit.text.clear()
                }
            }
            else {
                val toast = Toast.makeText(
                    this,
                    getText(R.string.input_error_msg),
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                toast.show()
            }

//            Snackbar.make(it, R.string.input_error_msg,Snackbar.LENGTH_SHORT).show()

        }

        binding.clearButton.setOnClickListener {
            viewModel.clear()
            binding.inputEdit.text.clear()
        }


    }

    private fun wordsToString(words: List<Word>):String{
        val stringBuilder = StringBuilder()
        for(word in words){
            if(word.count > 1)
                stringBuilder.append("${word.word} (${word.count})\n")
            else
                stringBuilder.append("${word.word}\n")
        }
        return stringBuilder.toString()
    }

    companion object{
//        private val INPUT_REGEX = Regex("""[A-Za-z-]""")
        private val INPUT_REGEX = Regex("""[A-Z]?[a-z]+-?[a-z]+""")
    }
}