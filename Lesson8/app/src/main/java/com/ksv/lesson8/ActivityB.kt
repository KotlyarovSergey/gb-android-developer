package com.ksv.lesson8

import android.app.slice.Slice
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.Slide
import android.view.Gravity

class ActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        with(window){
            enterTransition = Slide(Gravity.LEFT)
//            enterTransition = Explode()

//            exitTransition = Slide(Gravity.RIGHT)
            exitTransition = Explode()
        }
    }
}