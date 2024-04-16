package com.ksv.hw09_quiz.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ksv.hw09_quiz.R
import com.ksv.hw09_quiz.databinding.FragmentQuizBinding
import com.ksv.hw09_quiz.quiz.QuizStorage

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
        hideAllViews()
        setListeners()
        fadeInView(binding.questionView1)
    }

    private fun fillQuiz(){
//        binding.questionView1.setAllData(QuizStorage.Question1)
//        binding.questionView2.setAllData(QuizStorage.Question2)
//        binding.questionView3.setAllData(QuizStorage.Question3)
        QuizStorage.getLocaleQuestion(1)?.let { binding.questionView1.setAllData(it) }
        QuizStorage.getLocaleQuestion(2)?.let { binding.questionView2.setAllData(it) }
        QuizStorage.getLocaleQuestion(3)?.let { binding.questionView3.setAllData(it) }
    }

    private fun countRightAnswers(): Int{
        var cnt = 0
        if(binding.questionView1.isRightAnswer()) cnt++
        if(binding.questionView2.isRightAnswer()) cnt++
        if(binding.questionView3.isRightAnswer()) cnt++
        return cnt
    }

    private fun hideAllViews(){
        binding.questionView1.visibility = View.INVISIBLE
        binding.questionView2.visibility = View.INVISIBLE
        binding.questionView3.visibility = View.INVISIBLE
        binding.sendAnswers.visibility = View.GONE
        binding.questionView1.alpha = 0f
        binding.questionView2.alpha = 0f
        binding.questionView3.alpha = 0f
        binding.sendAnswers.alpha = 0f
    }

    private fun fadeInView(view: View){
        view.visibility = View.VISIBLE
        view.animate().apply {
            duration = 1000
            alpha(1.0f)
        }.start()
    }

    private fun setListeners(){
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

        binding.questionView1.radioChecked = {
            if(binding.questionView2.visibility == View.INVISIBLE)
                fadeInView(binding.questionView2)
        }
        binding.questionView2.radioChecked = {
            if(binding.questionView3.visibility == View.INVISIBLE)
                fadeInView(binding.questionView3)
        }
        binding.questionView3.radioChecked = {
            if(binding.sendAnswers.visibility == View.GONE)
                fadeInView(binding.sendAnswers)
        }
    }
}