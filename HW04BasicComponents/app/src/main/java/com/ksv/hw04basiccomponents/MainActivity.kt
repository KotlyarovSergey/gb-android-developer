package com.ksv.hw04basiccomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.ksv.hw04basiccomponents.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            textInputName.doOnTextChanged { _, _, _, _ ->
                checkAbilityToSaveAndCalcProgress()
            }

            textInputPhone.doOnTextChanged { _, _, _, _ ->
                checkAbilityToSaveAndCalcProgress()
            }

            radioGroupSex.setOnCheckedChangeListener { _, _ ->
                checkAbilityToSaveAndCalcProgress()
            }

            notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
                checkAbilityToSaveAndCalcProgress()
                checkAuthorisation.isEnabled = isChecked
                checkNewProducts.isEnabled = isChecked
            }

            checkAuthorisation.setOnClickListener {
                checkAbilityToSaveAndCalcProgress()
            }

            checkNewProducts.setOnClickListener {
                checkAbilityToSaveAndCalcProgress()
            }

            btnSave.setOnClickListener {
                save()
            }
        }
    }


    private fun checkAbilityToSaveAndCalcProgress() {
//        abilityToSave()
        calculateProgress()
        binding.btnSave.isEnabled = binding.linearProgress.progress == 100
    }

//    private fun abilityToSave() {
//        binding.btnSave.isEnabled = checkName() &&
//                checkPhone() &&
//                checkSex() &&
//                checkNotify()
//    }

    private fun calculateProgress() {
        with(binding) {
            var progress = 0
            if (checkName()) progress += 25
            if (checkPhone()) progress += 25
            if (checkSex()) progress += 25
            if (checkNotify()) progress += 25

            linearProgress.progress = progress

            val text = "$progress/100"
            tvScoresPoints.text = text
        }


    }

    private fun checkName(): Boolean = !binding.textInputName.text.isNullOrBlank() &&
            binding.textInputName.text.toString().length < 40

    private fun checkPhone(): Boolean = !binding.textInputPhone.text.isNullOrBlank()

    private fun checkSex(): Boolean = binding.radioMan.isChecked || binding.radioWoman.isChecked

    private fun checkNotify(): Boolean = binding.notificationSwitch.isChecked &&
            (binding.checkAuthorisation.isChecked ||
                    binding.checkNewProducts.isChecked)

    private fun save() {
        Toast.makeText(
            this,
            getString(R.string.save_successful),
            Toast.LENGTH_SHORT
        ).show()
    }
}