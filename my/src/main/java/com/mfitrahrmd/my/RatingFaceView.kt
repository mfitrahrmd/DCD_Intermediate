package com.mfitrahrmd.my

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Region
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.ResourcesCompat

class RatingFaceView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {
    private val bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)
    private val paint = Paint()
    private val halfOfWidth = (bitmap.width / 2).toFloat()
    private val halfOfHeight = (bitmap.height / 2).toFloat()
    private val left = 150F
    private val top = 250F
    private val right = bitmap.width - left
    private val bottom = bitmap.height.toFloat() - 50F
    private var isHappy = true

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(bitmap.width, bitmap.height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setImageBitmap(bitmap)
        showEars(canvas)
        showFace(canvas)
        showEyes(canvas)
        showMouth(canvas)
        showNose(canvas)
        showHair(canvas)
    }

    fun setHappy(isHappy: Boolean) {
        this.isHappy = isHappy
        invalidate()
    }

    private fun showFace(canvas: Canvas) {
        val face = RectF(left, top, right, bottom)
        paint.color = ResourcesCompat.getColor(resources, R.color.yellow_left_skin, null)
        canvas.drawArc(face, 90f, 180f, false, paint)
        paint.color = ResourcesCompat.getColor(resources, R.color.yellow_right_skin, null)
        canvas.drawArc(face, 270f, 180f, false, paint)
    }

    private fun showEyes(canvas: Canvas) {
        paint.color = ResourcesCompat.getColor(resources, R.color.black, null)
        canvas.drawCircle(halfOfWidth - 100F, halfOfHeight - 10F, 50F, paint)
        canvas.drawCircle(halfOfWidth + 100F, halfOfHeight - 10F, 50F, paint)
        paint.color = ResourcesCompat.getColor(resources, R.color.white, null)
        canvas.drawCircle(halfOfWidth - 120F, halfOfHeight - 20F, 15f, paint)
        canvas.drawCircle(halfOfWidth + 80, halfOfHeight - 20F, 15f, paint)
    }

    private fun showMouth(canvas: Canvas) {
        if (isHappy) {
            paint.color = ResourcesCompat.getColor(resources, R.color.black, null)
            val lip = RectF(
                halfOfWidth - 200F,
                halfOfHeight - 100F,
                halfOfWidth + 200F,
                halfOfHeight + 400F
            )
            canvas.drawArc(lip, 25f, 130f, false, paint)
            paint.color = ResourcesCompat.getColor(resources, R.color.white, null)
            val mouth =
                RectF(halfOfWidth - 180f, halfOfHeight, halfOfWidth + 180f, halfOfHeight + 380f)
            canvas.drawArc(mouth, 25f, 130f, false, paint)
        } else {
            paint.color = ResourcesCompat.getColor(resources, R.color.black, null)
            val lip = RectF(
                halfOfWidth - 200F,
                halfOfHeight + 250F,
                halfOfWidth + 200F,
                halfOfHeight + 350F
            )
            canvas.drawArc(lip, 0F, -180F, false, paint)
            paint.color = ResourcesCompat.getColor(resources, R.color.white, null)
            val mouth = RectF(
                halfOfWidth - 180F,
                halfOfHeight + 260F,
                halfOfWidth + 180F,
                halfOfHeight + 330F
            )
            canvas.drawArc(mouth, 0F, -180F, false, paint)
        }
    }

    private fun showNose(canvas: Canvas) {
        paint.color = ResourcesCompat.getColor(resources, R.color.black, null)
        canvas.drawCircle(halfOfWidth - 40f, halfOfHeight + 140f, 15f, paint)
        canvas.drawCircle(halfOfWidth + 40f, halfOfHeight + 140f, 15f, paint)
    }

    private fun showEars(canvas: Canvas) {
        paint.color = ResourcesCompat.getColor(resources, R.color.brown_left_hair, null)
        canvas.drawCircle(halfOfWidth - 300F, halfOfHeight - 100F, 100F, paint)
        paint.color = ResourcesCompat.getColor(resources, R.color.brown_right_hair, null)
        canvas.drawCircle(halfOfWidth + 300F, halfOfHeight - 100F, 100F, paint)
        paint.color = ResourcesCompat.getColor(resources, R.color.red_ear, null)
        canvas.drawCircle(halfOfWidth - 300F, halfOfHeight - 100F, 60f, paint)
        canvas.drawCircle(halfOfWidth + 300F, halfOfHeight - 100F, 60f, paint)
    }

    private fun showHair(canvas: Canvas) {
        canvas.save()
        val path = Path()
        path.addCircle(halfOfWidth - 100f, halfOfHeight - 10f, 150f, Path.Direction.CCW)
        path.addCircle(halfOfWidth + 100f, halfOfHeight - 10f, 150f, Path.Direction.CCW)
        val mouth = RectF(halfOfWidth - 250f, halfOfHeight, halfOfWidth + 250f, halfOfHeight + 500f)
        path.addOval(mouth, Path.Direction.CCW)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            canvas.clipPath(path, Region.Op.DIFFERENCE)
        } else {
            canvas.clipOutPath(path)
        }
        val face = RectF(left, top, right, bottom)
        paint.color = ResourcesCompat.getColor(resources, R.color.brown_left_hair, null)
        canvas.drawArc(face, 90f, 180f, false, paint)
        paint.color = ResourcesCompat.getColor(resources, R.color.brown_right_hair, null)
        canvas.drawArc(face, 270f, 180f, false, paint)
        canvas.restore()
    }
}