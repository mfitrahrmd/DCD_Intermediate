package com.mfitrahrmd.my

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.TriangleEdgeTreatment

class TicketTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {
    private val triangleEdge = TriangleEdgeTreatment(10f.dpToPx(), true)
    private val circleEdge = BottomAppBarTopEdgeTreatment(0f,0f,0f).apply {
        fabDiameter = 16f.dpToPx()
    }
    private val shapeAppearanceModel = ShapeAppearanceModel.Builder()
        .setLeftEdge(triangleEdge)
        .setRightEdge(triangleEdge)
        .setTopEdge(circleEdge)
        .setAllCornerSizes(12f.dpToPx())
        .build()
    private val materialShapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
        setTint(ContextCompat.getColor(context, R.color.grey_200))
        strokeWidth = 2f.dpToPx()
        strokeColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.black))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        background = materialShapeDrawable
    }
}