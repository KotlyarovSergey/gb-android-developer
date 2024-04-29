package com.ksv.lesson13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.ksv.lesson13.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pet = Pet("Мурзик", 2, 3.12, false)
        binding.pet = pet



        binding.weightHide.setOnClickListener {
            pet.isVisible = !pet.isVisible
            binding.invalidateAll()

        }

        val petList = mutableListOf(
            Pet("Барсик", 4, 1.15, true),
            Pet("Черныш", 2, 3.54, true)
        )

        val petMap = mutableMapOf(
            "first" to Pet("Боцман", 3, 534.4, true),
            "second" to Pet("Лоцман", 3, 534.4, true)
        )

        lifecycleScope.launch {
            delay(10_000)
            val newPet = Pet(pet.name, pet.age, pet.weight, true)
            binding.pet = newPet
            binding.weightHide.visibility = View.GONE
            petList.add(Pet("Гадёныш", 2, 3.54, true))
            petMap["first"] = Pet("Шкипер", 3, 534.4, true)
            binding.invalidateAll()
        }

        binding.pets = petList
        binding.petsMap = petMap

//        binding.helper = Helper()
    }
}