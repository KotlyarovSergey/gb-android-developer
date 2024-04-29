package com.ksv.lesson13

import android.content.Context
import android.view.View
import android.widget.Toast

object Helper {
    @JvmStatic
    fun onClick(c: Context){
        Toast.makeText(c, "OnClick", Toast.LENGTH_SHORT).show()
    }

    @JvmStatic
    fun onTextChanged(v: View, string: String){
        Toast.makeText(v.context, "OnTextChanged $string", Toast.LENGTH_SHORT).show()
    }

    @JvmStatic
    fun onChecked(v: View, checked: Boolean){
        Toast.makeText(v.context, "OnTextChanged $checked", Toast.LENGTH_SHORT).show()
    }
}