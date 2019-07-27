package ru.skillbranch.devintensive.utils

import android.graphics.*
import android.graphics.drawable.Drawable
import kotlin.math.min

class TextDrawable(val text : String, val color : Int, val width : Float, val height : Float) : Drawable() {

    val paint = Paint()

    init {
        paint.textSize = 100f
        paint.isAntiAlias = true
        paint.isFakeBoldText = true
        paint.setShadowLayer(6f, 0f, 0f, Color.WHITE)
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
        canvas.drawText(text, halfWidth, halfHeight, paint)

//        circlePaint.color = ;
//        canvas?.drawCircle(halfWidth, halfHeight, radius - borderWidth, circlePaint)
//
//        circlePaint.color = Color.WHITE
//        circlePaint.textAlign = Paint.Align.CENTER
//        circlePaint.textSize = 50.0f
//        canvas?.drawText(Utils.toInitials(text, halfWidth, halfHeight, circlePaint)

//        if (et_first_name != null && et_last_name != null) {
//            circlePaint.color = context.getColor(R.color.color_accent);
//            canvas?.drawCircle(halfWidth, halfHeight, radius - borderWidth, circlePaint)
//
//            circlePaint.color = Color.WHITE
//            circlePaint.textAlign = Paint.Align.CENTER
//            circlePaint.textSize = 50.0f
//            canvas?.drawText(Utils.toInitials(et_first_name.text.toString(), et_last_name.text.toString()) ?: "",
//                halfWidth, halfHeight, circlePaint)
//        } else {
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