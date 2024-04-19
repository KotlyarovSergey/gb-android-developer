package com.ksv.gb.hw10lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.ksv.gb.hw10lifecycle.databinding.ActivityMainBinding
import java.lang.Thread.sleep
import java.util.Calendar
import kotlin.concurrent.thread
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isTimerRun = false
    private var endTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preparingViews()
        checkBundle(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("ksvlog", "saveInstance")
        outState.putBoolean(TIMER_RUN, isTimerRun)
        outState.putFloat(SLIDER_VALUE, binding.slider.value)
        outState.putLong(END_TIME, endTime)
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val MAX_TIMER = 100
        private const val SLIDER_STEP = 20
        private const val REFRESH_SPAN = 100L
        private const val SLIDER_VALUE = "slider_value"
        private const val TIMER_RUN = "timer_run"
        private const val END_TIME = "end_time"
    }

    private fun preparingViews() {
        with(binding.slider) {
            valueFrom = 0f
            valueTo = MAX_TIMER.toFloat()
            stepSize = SLIDER_STEP.toFloat()
            addOnChangeListener { _, value, _ ->
                binding.tvProgress.text = value.roundToInt().toString()
            }
        }

        binding.progress.max = MAX_TIMER
        binding.progress.progress = MAX_TIMER

        binding.startButton.setOnClickListener {
            if (isTimerRun) {
                breakTimer()
            } else {
                if (binding.slider.value > 0) {
                    endTime =
                        Calendar.getInstance().timeInMillis + binding.slider.value.toLong() * 1000
                    startTimer()
                }
            }
        }
    }

    private fun checkBundle(savedInstanceState: Bundle?) {
        savedInstanceState?.let { bundle ->
            isTimerRun = bundle.getBoolean(TIMER_RUN, false)
            endTime = bundle.getLong(END_TIME, 0L)
            binding.slider.value = bundle.getFloat(SLIDER_VALUE, 0f)
        }

        if (isTimerRun)
            startTimer()
    }

    private fun startTimer() {
        binding.slider.isEnabled = false
        binding.startButton.text = getString(R.string.btn_stop_text)
        binding.progress.max = binding.slider.value.roundToInt()
        isTimerRun = true

        thread {
            while (isNoTime()) {
                Log.d("ksvlog", "is work: ${Thread.currentThread().name}")
                runOnUiThread {
                    refreshViewsIfIsNeed()
                }
                sleep(REFRESH_SPAN)
            }
            runOnUiThread {
                stopTimer()
            }
        }

    }

    private fun isNoTime(): Boolean {
        val currentTime = Calendar.getInstance().timeInMillis
        return currentTime < endTime
    }

    private fun refreshViews() {
        val lost = lostSeconds()
        binding.progress.progress = lost
        binding.tvProgress.text = lost.toString()
    }

    private fun refreshViewsIfIsNeed() {
        val lost = lostSeconds()
        if (binding.progress.progress != lost) {
            binding.progress.progress = lost
            binding.tvProgress.text = lost.toString()
        }
    }

    private fun lostSeconds(): Int {
        val currentTime = Calendar.getInstance().timeInMillis
        return ((endTime - currentTime) / 1000).toInt()
    }

    private fun stopTimer() {
        binding.slider.isEnabled = true
        binding.startButton.text = getString(R.string.btn_start_text)
        binding.slider.value = 0f
        binding.progress.progress = binding.progress.max
        isTimerRun = false
    }

    private fun breakTimer() {
        endTime = 0L
    }
}