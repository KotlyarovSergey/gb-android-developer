package com.ksv.hw07_quiz.ui.main

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.ksv.hw07_quiz.R
import com.ksv.hw07_quiz.databinding.QuestionViewBinding
import com.ksv.hw07_quiz.quiz.QuizStorage

class QuestionView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
): LinearLayout(context, attrs){
    private val binding: QuestionViewBinding
    private var rightAnswer = -1
    init {
        val inflatedView = inflate(context, R.layout.question_view, this)
        binding = QuestionViewBinding.bind(inflatedView)
    }


    fun isRightAnswer(): Boolean{
        if(rightAnswer == -1) return false
        when(rightAnswer){
            1 -> return binding.answer1.isChecked
            2 -> return binding.answer2.isChecked
            3 -> return binding.answer3.isChecked
        }
        return false
    }

    fun setAllData(data: QuizStorage.QuestionData){
        with(binding) {
            tvQuestion.text = data.text
            answer1.text = data.answer1
            answer2.text = data.answer2
            answer3.text = data.answer3
        }
        rightAnswer = data.right
    }
    fun setRightAnswer(number: Int){
        rightAnswer = number
    }
    fun setQuestionText(text: String){
        binding.tvQuestion.text = text
    }

    fun setAnswer1Text(text: String){
        binding.answer1.text = text
    }
    fun setAnswer2Text(text: String){
        binding.answer2.text = text
    }
    fun setAnswer3Text(text: String){
        binding.answer3.text = text
    }

}