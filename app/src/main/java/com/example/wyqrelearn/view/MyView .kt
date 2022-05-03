package com.example.wyqrelearn.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.wyqrelearn.R
import com.example.wyqrelearn.dp2px

class MyView : View {

    private val BITMAP_WIDTH = 200.dp2px
    private val BITMAP_PADDING = 100.dp2px
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val camera = Camera().apply {
        rotateX(30f)
        setLocation(0f, 0f, -6 * resources.displayMetrics.density)
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    private val bitmap = getAvatar(BITMAP_WIDTH.toInt())

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
    }

    fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
    }
}

