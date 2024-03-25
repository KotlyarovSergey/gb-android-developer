package com.ksv.gb.lesson4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.Display.Mode
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.doOnTextChanged
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputLayout
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener { _, buttonId ->
            if(buttonId > 0){
                val btn = findViewById<RadioButton>(buttonId)
                if(btn.isChecked){
                    val txt = when(buttonId){
                        R.id.radio1 -> "Маша"
                        R.id.radio2 -> "Таня"
                        R.id.radio3 -> "Катя"
                        else -> throw RuntimeException("Radiobutton id: $buttonId unknown")
                    }
                    Toast.makeText(this, txt, Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Пусто", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.clearButton).setOnClickListener {
            radioGroup.clearCheck()
        }

        val inputLayout = findViewById<TextInputLayout>(R.id.emailLayout)
        val textInputLayout = findViewById<EditText>(R.id.etEmail)
        textInputLayout.doOnTextChanged { text, _, _, _ ->
            if(isValidEmail(text)){
                inputLayout.isErrorEnabled = false
            } else{
                textInputLayout.error = "неверный email"
                inputLayout.isErrorEnabled = true
            }
        }

        val themeChangeButton = findViewById<Button>(R.id.btnThemeChange)
        themeChangeButton.setOnClickListener {

            if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Toast.makeText(this, "Светлая тема", Toast.LENGTH_SHORT).show()
                themeChangeButton.text = "Night mode"
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Toast.makeText(this, "Тёмная тема", Toast.LENGTH_SHORT).show()
                themeChangeButton.text = "Day mode"
            }
        }

        val circleProgress = findViewById<CircularProgressIndicator>(R.id.circleProgress)
        circleProgress.isActivated = true
        circleProgress.progress = 35

    }

    private fun isValidEmail(email: CharSequence?): Boolean{
        return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}