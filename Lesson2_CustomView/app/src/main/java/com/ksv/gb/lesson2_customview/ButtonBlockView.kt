package com.ksv.gb.lesson2_customview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.LinearLayout
import com.ksv.gb.lesson2_customview.databinding.ButtonBlocksViewBinding

class ButtonBlockView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {

//    init {
//        inflate(context, R.layout.button_blocks_view, this)
//    }

    private val binding: ButtonBlocksViewBinding
    init {
        val inflatedView = inflate(context, R.layout.button_blocks_view, this)
        binding = ButtonBlocksViewBinding.bind(inflatedView)

        binding.button.setOnClickListener {
            buttonClicked?.invoke()
        }

        val typedArray = context.obtainStyledAttributes(attrs,
            R.styleable.ButtonBlockView, 0, 0)

        val messageText = typedArray.getString(R.styleable.ButtonBlockView_message_text)
        binding.message.text = messageText

        val buttonText = typedArray.getString(R.styleable.ButtonBlockView_button_text)
        binding.button.text = buttonText

        val backgroundColor = typedArray.getColor(R.styleable.ButtonBlockView_background_color,
            Color.WHITE)
        binding.root.setBackgroundColor(backgroundColor)

        typedArray.recycle()
    }

    fun setMessageText(message: String) {
        binding.message.text = message
    }
    fun setButtonText(message: String) {
        binding.button.text = message
    }
    fun changeBackgroundColor(color: Int) {
        binding.root.setBackgroundColor(color)
//        binding.message.setTextColor(color)

    }

    var buttonClicked: (() -> Unit)? = null
}