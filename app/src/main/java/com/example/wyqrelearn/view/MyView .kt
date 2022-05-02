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

    override fun onDraw(canvas: Canvas) {
        canvas.save()
        canvas.translate(BITMAP_PADDING + BITMAP_WIDTH / 2, BITMAP_PADDING + BITMAP_WIDTH / 2)
        canvas.rotate(-30f)
        canvas.clipRect(
            -BITMAP_WIDTH,
            -BITMAP_WIDTH,
            BITMAP_WIDTH,
            0f
        )
        canvas.rotate(30f)
        canvas.translate(-(BITMAP_PADDING + BITMAP_WIDTH / 2), -(BITMAP_PADDING + BITMAP_WIDTH / 2))
        canvas.drawBitmap(getAvatar(BITMAP_WIDTH.toInt()), BITMAP_PADDING, BITMAP_PADDING, paint)
        canvas.restore()

        canvas.save()
        canvas.translate(BITMAP_PADDING + BITMAP_WIDTH / 2, BITMAP_PADDING + BITMAP_WIDTH / 2)
        canvas.rotate(-30f)
        camera.applyToCanvas(canvas)
        canvas.clipRect(
            -BITMAP_WIDTH,
            0f,
            BITMAP_WIDTH,
            BITMAP_WIDTH
        )
        canvas.rotate(30f)
        canvas.translate(-(BITMAP_PADDING + BITMAP_WIDTH / 2), -(BITMAP_PADDING + BITMAP_WIDTH / 2))
        canvas.drawBitmap(getAvatar(BITMAP_WIDTH.toInt()), BITMAP_PADDING, BITMAP_PADDING, paint)
        canvas.restore()
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

