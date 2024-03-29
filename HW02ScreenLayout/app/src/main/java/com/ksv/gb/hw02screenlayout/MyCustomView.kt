package com.ksv.gb.hw02screenlayout

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.ksv.gb.hw02screenlayout.databinding.MyCustomViewBinding

class MyCustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    private val binding: MyCustomViewBinding
    init {
        val inflatedView = inflate(context, R.layout.my_custom_view, this)
        binding = MyCustomViewBinding.bind(inflatedView)
    }

    fun setTopText(txt: String){
        binding.topText.text = txt
    }
    fun setBottomText(txt: String){
        binding.bottomText.text = txt
    }
}