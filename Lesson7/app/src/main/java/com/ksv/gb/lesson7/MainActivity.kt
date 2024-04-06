package com.ksv.gb.lesson7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            replace<FirstFragment>(R.id.fragment_container_on_activity)
            addToBackStack(FirstFragment::class.java.simpleName)
        }
    }
}