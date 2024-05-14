package com.mfitrahrmd.my

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

class ClockView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {
    private var width: Int = 0
    private var height: Int = 0
    private var centerX: Float = 0f
    private var centerY: Float = 0f
    private val length: Float = 100f
    private val paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.pink_500)
        strokeWidth = 2f.dpToPx()
    }
    private var second: Int = 0

    fun animateClock() {
        val valuesHolder = PropertyValuesHolder.ofInt(SECOND_VALUE_HOLDER, 0, 60)
        ValueAnimator().apply {
            setValues(valuesHolder)
            duration = 60000
            interpolator = LinearInterpolator()
            addUpdateListener {
                second = it.getAnimatedValue(SECOND_VALUE_HOLDER) as Int
                invalidate()
            }
            start()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        height = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        centerX = (width / 2).toFloat()
        centerY = (width / 2).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val endX = centerX + (length.dpToPx() * sin(Math.toRadians(second*6.0))).toFloat()
        val endY = centerY + (length.dpToPx() * -cos(Math.toRadians(second*6.0))).toFloat()
        canvas.drawLine(centerX, centerY, endX, endY, paint)
        canvas.drawCircle(centerX, centerY, 10f.dpToPx(), paint)
    }

    companion object {
        const val SECOND_VALUE_HOLDER = "second"
    }
}