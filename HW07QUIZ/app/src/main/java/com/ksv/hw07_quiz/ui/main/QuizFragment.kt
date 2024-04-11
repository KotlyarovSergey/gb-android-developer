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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuizFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuizFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun fillQuiz(){
        binding.questionView1.setAllData(QuizStorage.Question1)
        binding.questionView2.setAllData(QuizStorage.Question2)
        binding.questionView3.setAllData(QuizStorage.Question3)

//
//        var question = QuizStorage.Question1
//        with(binding.questionView1){
//            setQuestionText(question.text)
//            setAnswer1Text(question.answer1)
//            setAnswer2Text(question.answer2)
//            setAnswer3Text(question.answer3)
//            setRightAnswer(question.right)
//
//        }
//
//        question = QuizStorage.Question2
//        with(binding.questionView2){
//            setQuestionText(question.text)
//            setAnswer1Text(question.answer1)
//            setAnswer2Text(question.answer2)
//            setAnswer3Text(question.answer3)
//            setRightAnswer(question.right)
//        }
//
//        question = QuizStorage.Question3
//        with(binding.questionView3){
//            setQuestionText(question.text)
//            setAnswer1Text(question.answer1)
//            setAnswer2Text(question.answer2)
//            setAnswer3Text(question.answer3)
//            setRightAnswer(question.right)
//        }

    }

    private fun countRightAnswers(): Int{
        var cnt = 0
        if(binding.questionView1.isRightAnswer()) cnt++
        if(binding.questionView2.isRightAnswer()) cnt++
        if(binding.questionView3.isRightAnswer()) cnt++
        return cnt

    }
}