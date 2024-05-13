package com.mfitrahrmd.my

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity.CENTER
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class Button @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatButton(context, attrs) {
    private var textColor: Int = 0
    private var enabledBackground: Drawable
    private var disabledBackground: Drawable

    init {
        textColor = ContextCompat.getColor(context  , android.R.color.background_light)
        enabledBackground = ContextCompat.getDrawable(context, R.drawable.bg_button) as Drawable
        disabledBackground = ContextCompat.getDrawable(context, R.drawable.bg_button_disable) as Drawable
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        background = if (isEnabled) enabledBackground else disabledBackground
        setTextColor(textColor)
        textSize = 12f
        gravity = CENTER
        text = if(isEnabled) "Submit" else "Please fill in all fields"
    }
}