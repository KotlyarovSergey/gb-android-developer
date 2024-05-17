package com.ksv.lesson14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.ksv.lesson14.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                binding.radioRetrofit.id -> {
                    binding.tvStatus.text = "retrofit"
                    supportFragmentManager.commit {
                        replace<RetrofitFragment>(binding.fragmentContainer.id)
                        addToBackStack(RetrofitFragment::javaClass.name)
                    }
                }
                binding.radioGson.id -> {
                    binding.tvStatus.text = "gson"
                    supportFragmentManager.commit {
                        replace<GsonFragment>(binding.fragmentContainer.id)
                        addToBackStack(GsonFragment::javaClass.name)
                    }
                }
                binding.radioMoshi.id -> {
                    binding.tvStatus.text = "moshi"
                    supportFragmentManager.commit {
                        replace<MoshiFragment>(binding.fragmentContainer.id)
                        addToBackStack(MoshiFragment::javaClass.name)
                    }
                }
            }
        }

//        binding.radioRetrofit.isChecked = true
        binding.radioGson.isChecked = true
    }
}