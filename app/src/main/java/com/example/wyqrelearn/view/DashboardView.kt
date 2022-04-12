package com.example.wyqrelearn.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.wyqrelearn.dp2px
import kotlin.math.cos
import kotlin.math.sin

private val RADIUS = 150.dp2px
private const val OPEN_ANGLE = 120
private val DASH_WIDTH = 2.dp2px
private val DASH_HEIGHT = 5.dp2px
private val LINE_LENGTH = 120.dp2px

class DashboardView : View {

    private val paint = Paint()
    private val dash = Path()
    private val path = Path()
    lateinit var pathEffect: PathDashPathEffect

    init {
        paint.strokeWidth = 1.dp2px
        paint.style = Paint.Style.STROKE
        dash.addRect(0f, 0f, DASH_WIDTH, DASH_HEIGHT, Path.Direction.CW)
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.addArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            90f + OPEN_ANGLE / 2,
            360f - OPEN_ANGLE
        )
        val pathMeasure = PathMeasure(path, false)
        pathEffect = PathDashPathEffect(
            dash,
            (pathMeasure.length - DASH_WIDTH) / 20,
            0f,
            PathDashPathEffect.Style.ROTATE
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)

        paint.pathEffect = pathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null

        val ra = mark2Radian(5)
        canvas.drawLine(
            width / 2f, height / 2f,
            width / 2 + LINE_LENGTH * cos(ra).toFloat(),
            height / 2 + LINE_LENGTH * sin(ra).toFloat(),
            paint
        )
    }

    private fun mark2Radian(mark: Int) =
        Math.toRadians((90f + OPEN_ANGLE / 2 + (360f - OPEN_ANGLE) / 20 * mark).toDouble())
}

