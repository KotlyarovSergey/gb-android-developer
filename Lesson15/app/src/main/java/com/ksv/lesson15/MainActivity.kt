package com.ksv.lesson15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ksv.lesson15.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val userDao: UserDao = (application as App).db.userDao()
                return MainViewModel(userDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener { viewModel.onAddButton() }
        binding.updateButton.setOnClickListener { viewModel.onUpdateButton() }
        binding.deleteBbutton.setOnClickListener { viewModel.onDeleteButton() }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.allUsers
                    .collect {users ->
                        binding.tvResults.text = users.joinToString ("\n")
                    }
            }
        }

        binding.filterBbutton.setOnClickListener {
//            val users =
                viewModel.filterByName("name 1")
//            binding.tvResults.text = users.joinToString ("\n")
        }

        binding.updNameButton.setOnClickListener {
            val newName = binding.editText.text.toString()
            val users = viewModel.allUsers.value
            val num = (users.indices).random()
            val id = users[num].id
            viewModel.updateNameById(id, newName)
        }
    }
}