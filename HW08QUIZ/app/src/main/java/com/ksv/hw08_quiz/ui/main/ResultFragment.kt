package com.ksv.hw08_quiz.ui.main

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.content.ContextCompat.getColor
import androidx.core.graphics.toColor
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ksv.hw08_quiz.MainActivity
import com.ksv.hw08_quiz.R
import com.ksv.hw08_quiz.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private val args: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showResult()
        setAnimation()

        binding.newQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_quizFragment)
        }
    }

    private fun showResult() {
        val totalScores = args.totalScores
        val rightScores = args.rightScores
        val text = "$rightScores ${getString(R.string.result_balls_template)} $totalScores"
        binding.resultBall.text = text
    }

    private fun setAnimation() {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 1.2f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 1.2f)
        ObjectAnimator.ofPropertyValuesHolder(binding.newQuiz, scaleX, scaleY).apply {
            duration = 2000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()


        ObjectAnimator.ofArgb(
            binding.resultMessage,
            "textColor",
            Color.RED,
            Color.GREEN
        ).apply {
            duration = 2000
            interpolator = AccelerateDecelerateInterpolator()
            repeatMode = ObjectAnimator.REVERSE
            repeatCount = ObjectAnimator.INFINITE
        }.start()

        if (args.rightScores < 2)
            binding.lottieAnim.visibility = View.INVISIBLE
    }
}