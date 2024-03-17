package com.ksv.hw01_startofwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ksv.hw01_startofwork.databinding.ActivityMainBinding

const val MAX_COUNT = 50

class MainActivity : AppCompatActivity() {
    private var counter = 0
        set(value) {
            field = value
            valueChanged()
        }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnMinus.isEnabled = false

        binding.btnMinus.setOnClickListener { counter-- }
        binding.btnPlus.setOnClickListener { counter++ }
        binding.btnReset.setOnClickListener {
            counter = 0
            binding.btnReset.visibility = View.INVISIBLE
        }
    }

    private fun valueChanged() {
        binding.tvCounter.text = counter.toString()
        when (counter) {
            0 -> {
                binding.btnMinus.isEnabled = false
                binding.tvStatus.text = getString(R.string.tv_status_title)
                binding.tvStatus.setTextColor(getColor(R.color.green))
            }

            in 1 until MAX_COUNT -> {
                binding.btnMinus.isEnabled = true
                val text =
                    "${binding.tvStatus.context.getString(R.string.tv_status_free_seats)} ${MAX_COUNT - counter}"
                binding.tvStatus.text = text
                binding.tvStatus.setTextColor(getColor(R.color.blue))
            }

            MAX_COUNT -> {
                binding.btnReset.visibility = View.VISIBLE
                binding.tvStatus.text =
                    binding.tvStatus.context.getString(R.string.tv_status_overload)
                binding.tvStatus.setTextColor(getColor(R.color.red))
            }
        }
    }
}