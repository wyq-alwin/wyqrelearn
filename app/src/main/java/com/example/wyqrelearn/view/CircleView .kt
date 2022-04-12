package com.example.wyqrelearn.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.wyqrelearn.dp2px
import java.lang.Math.min

private val CIRCLE_PADDING = 50.dp2px
private val RADIUS = 100.dp2px

class CircleView : View {

    private val paint = Paint()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = resolveSize(((RADIUS+ CIRCLE_PADDING) * 2).toInt(),widthMeasureSpec)
        val height = resolveSize(((RADIUS+ CIRCLE_PADDING) * 2).toInt(),heightMeasureSpec)
        setMeasuredDimension(width, height)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(width / 2f, height / 2f, RADIUS, paint)
    }

}

