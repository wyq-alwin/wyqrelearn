package com.example.wyqrelearn.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.wyqrelearn.dp2px
import kotlin.math.cos
import kotlin.math.sin

private val RADIUS = 150.dp2px
private val ANGELS = floatArrayOf(60f, 90f, 150f, 60f)
private val COLORS = listOf(Color.GREEN, Color.GRAY, Color.BLUE, Color.RED)
private val OFFSET_LENGTH = 20.dp2px

class PieView : View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.strokeWidth = 3.dp2px
        paint.style = Paint.Style.FILL
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val n1 = MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST
        val n2 = MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED
        val n3 = MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY
        val n4 = MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY
        println()
    }

    override fun onDraw(canvas: Canvas) {
        var startAngle = 0f
        ANGELS.forEachIndexed { index, angle ->
            if (index == 1) {
                canvas.save()
                canvas.translate(
                    OFFSET_LENGTH * cos((Math.toRadians((startAngle + angle / 2).toDouble()))).toFloat(),
                    OFFSET_LENGTH * sin((Math.toRadians((startAngle + angle / 2).toDouble()))).toFloat()
                )
            }
            paint.color = COLORS[index]
            canvas.drawArc(
                width / 2f - RADIUS,
                height / 2f - RADIUS,
                width / 2f + RADIUS,
                height / 2f + RADIUS,
                startAngle,
                angle,
                true,
                paint
            )
            startAngle += angle
            if (index == 1) {
                canvas.restore()
            }
        }

    }

}

