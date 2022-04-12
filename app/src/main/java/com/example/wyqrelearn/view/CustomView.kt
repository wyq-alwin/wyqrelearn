package com.example.wyqrelearn.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import com.example.wyqrelearn.dp2px

class CustomView : View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 4.dp2px
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }
    private val paths = SparseArray<Path>()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas) {
        for (i in 0 until paths.size()) {
            canvas.drawPath(paths.valueAt(i), paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val path = Path()
                path.moveTo(event.getX(event.actionIndex), event.getY(event.actionIndex))
                paths.append(event.getPointerId(event.actionIndex), path)
                invalidate()
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                paths.remove(event.getPointerId(event.actionIndex))
                invalidate()
            }

            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until paths.size()) {
                    paths.get(event.getPointerId(i)).lineTo(event.getX(i), event.getY(i))
                }
                invalidate()
            }
        }
        return true
    }
}