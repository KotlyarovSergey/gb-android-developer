package com.ksv.hw09_quiz.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.ksv.hw09_quiz.R
import com.ksv.hw09_quiz.databinding.FragmentGreetingBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class GreetingFragment : Fragment() {
    private var _binding: FragmentGreetingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGreetingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startButton.setOnClickListener {
            findNavController().navigate(R.id.action_greetingFragment_to_quizFragment)
        }
        binding.birthButton.setOnClickListener {
            checkoutBirthDay()
        }
    }

    private fun checkoutBirthDay(){
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(getString(R.string.date_format))
        val dialog = MaterialDatePicker.Builder.datePicker()
            .setTitleText(getString(R.string.birth_dialog_picker_title))
            .build()

        dialog.addOnPositiveButtonClickListener {
            calendar.timeInMillis = it
            Snackbar.make(binding.birthButton, dateFormat.format(calendar.time), Snackbar.LENGTH_SHORT).show()
        }

        dialog.show(parentFragmentManager, "DatePicker")
    }
}