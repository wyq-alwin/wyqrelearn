package com.example.wyqrelearn.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.wyqrelearn.R
import com.example.wyqrelearn.dp2px

private val BITMAP_WIDTH = 150.dp2px
private val BITMAP_PADDING = 50.dp2px

class AvatarView : View {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    private val bitmap = getAvatar(150.dp2px.toInt())
    private val path = Path()

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)


    override fun onDraw(canvas: Canvas) {
        path.addOval(
            BITMAP_PADDING, BITMAP_PADDING,
            BITMAP_PADDING + BITMAP_WIDTH, BITMAP_PADDING + BITMAP_WIDTH,
            Path.Direction.CW
        )
        canvas.clipPath(path)
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

