package com.example.wyqrelearn.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.View
import com.example.wyqrelearn.dp2px


class DrawableView : View {
    val drawable = ColorDrawable(Color.RED)

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)


    override fun onDraw(canvas: Canvas) {
        drawable.setBounds(100.dp2px.toInt(), 100.dp2px.toInt(), width, height)
        drawable.draw(canvas)
    }
}

