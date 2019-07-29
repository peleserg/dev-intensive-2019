package ru.skillbranch.devintensive.utils

import android.graphics.*
import android.graphics.drawable.Drawable
import kotlin.math.min

class TextDrawable(val text : String, val color : Int, val width : Float, val height : Float) : Drawable() {

    val paint = Paint()

    init {
        paint.textSize = height / 2
        paint.isAntiAlias = true
        paint.isFakeBoldText = true
        paint.style = Paint.Style.FILL
        paint.textAlign = Paint.Align.CENTER
    }

    override fun draw(canvas: Canvas) {
        val halfWidth = width / 2
        val halfHeight = height / 2
        val radius = min(halfWidth, halfHeight)

        paint.color = color
        canvas.drawCircle(halfWidth, halfHeight, radius, paint)
        paint.color = Color.WHITE
        canvas.drawText(text, halfWidth, halfHeight - (paint.descent() + paint.ascent()) / 2, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT;
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }
}