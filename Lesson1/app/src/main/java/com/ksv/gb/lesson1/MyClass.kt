package com.ksv.gb.lesson1

import android.content.Context
import android.renderscript.ScriptGroup.Binding
import com.ksv.gb.lesson1.databinding.ActivityMainBinding

class MyClass() {
    lateinit var bnd: ActivityMainBinding
    fun doit(binding: ActivityMainBinding){
        val cntext = binding.testTextView.context
        binding.testTextView.text = cntext.getText(R.string.testText)
        binding.testTextView.setTextColor(cntext.getColor(R.color.pink))
        bnd = binding
    }

    fun withContext(cntx: Context){
        val pinkColor = cntx.getColor(R.color.pink)
        bnd.testTextView.setTextColor(cntx.getColor(R.color.black))

    }
}