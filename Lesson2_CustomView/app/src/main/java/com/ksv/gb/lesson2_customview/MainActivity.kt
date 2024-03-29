package com.ksv.gb.lesson2_customview

import android.graphics.Color
import android.graphics.ColorSpace.Rgb
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ksv.gb.lesson2_customview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.blockView.setMessageText("А это текст сообщения, выставленный программно")
//        binding.blockView.setButtonText("Текст кнопки выставлен программно")
        //binding.blockView.changeBackgroundColor(Color.YELLOW)

        binding.changeButton.setOnClickListener {
            val rnd = (0..100000).random().toString()
            binding.blockView.setButtonText(rnd)
        }

        binding.blockView.buttonClicked = {
            val color = Color.argb(
                    (0..255).random(),
                    (0..255).random(),
                    (0..255).random(),
                    (0..255).random())
            binding.blockView.changeBackgroundColor(color)
        }
    }
}