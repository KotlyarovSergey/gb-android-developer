package com.ksv.hw07_quiz.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ksv.hw07_quiz.R
import com.ksv.hw07_quiz.databinding.FragmentQuizBinding
import com.ksv.hw07_quiz.quiz.QuizStorage

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillQuiz()

        binding.sendAnswers.setOnClickListener {
            val totalQuestions = 3
            val rightAnswers = countRightAnswers()

            val action = QuizFragmentDirections.actionQuizFragmentToResultFragment()
            action.totalScores = totalQuestions
            action.rightScores = rightAnswers
            findNavController().navigate(action)
        }

        binding.backToGreeting.setOnClickListener {
            findNavController().navigate(R.id.action_quizFragment_to_greetingFragment)
        }
    }

    private fun fillQuiz(){
        binding.questionView1.setAllData(QuizStorage.Question1)
        binding.questionView2.setAllData(QuizStorage.Question2)
        binding.questionView3.setAllData(QuizStorage.Question3)
    }

    private fun countRightAnswers(): Int{
        var cnt = 0
        if(binding.questionView1.isRightAnswer()) cnt++
        if(binding.questionView2.isRightAnswer()) cnt++
        if(binding.questionView3.isRightAnswer()) cnt++
        return cnt
    }
}