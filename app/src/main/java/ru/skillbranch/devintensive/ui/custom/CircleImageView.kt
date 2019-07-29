package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.ColorRes
import ru.skillbranch.devintensive.R
import kotlin.math.min
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toColorInt

class CircleImageView @JvmOverloads constructor(
    context : Context,
    attrs : AttributeSet? = null,
    defStyleAttr : Int = 0
) : ImageView(context, attrs, defStyleAttr) {
    companion object {
        private const val DEFAULT_BORDER_COLOR : Int = Color.WHITE
        private const val DEFAULT_BORDER_WIDTH : Int = 2
    }

    private var borderColor : Int = DEFAULT_BORDER_COLOR
    private var borderWidth : Int = DEFAULT_BORDER_WIDTH

    lateinit var bitmap : Bitmap
    lateinit var bitmapShader : BitmapShader

    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            borderColor = a.getInt(R.styleable.CircleImageView_cv_borderColor, DEFAULT_BORDER_COLOR)
            borderWidth = a.getDimensionPixelSize(R.styleable.CircleImageView_cv_borderWidth, DEFAULT_BORDER_WIDTH)
            a.recycle()
        }
    }

    fun getBorderWidth() : Int = borderWidth

    fun setBorderWidth(dp : Int) {
        borderWidth = dp
    }

    fun getBorderColor() : Int = borderColor

    fun setBorderColor(hex : String) {
        borderColor = hex.toColorInt()
    }

    fun setBorderColor(@ColorRes colorId: Int) {
        borderColor = colorId
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val halfWidth = (measuredWidth / 2).toFloat()
        val halfHeight = (measuredHeight / 2).toFloat()
        val radius = min(halfWidth, halfHeight)

        val circlePaint = Paint()
        circlePaint.style = Paint.Style.FILL
        circlePaint.isAntiAlias = true
        circlePaint.color = borderColor
        canvas?.drawCircle(halfWidth, halfHeight, radius, circlePaint)

        bitmap = drawable.toBitmap(measuredWidth, measuredHeight)
        bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        circlePaint.shader = bitmapShader
        canvas?.drawCircle(halfWidth, halfHeight, radius - borderWidth, circlePaint)
    }
}
