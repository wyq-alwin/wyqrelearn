package com.example.wyqrelearn.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import com.example.wyqrelearn.dp2px
import com.example.wyqrelearn.getAvatar

private val AVATAR_WIDTH = 200.dp2px.toInt()

class ScalableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val avatar = getAvatar(resources, AVATAR_WIDTH)
    private var originOffsetX = 0f
    private var originOffsetY = 0f
    private var offsetX = 0f
    private var offsetY = 0f
    private var bigScale = 0f
    private var big = false
    private var curScale = 1f
        set(value) {
            field = value
            invalidate()
        }
    private val scaleAnimator by lazy {
        ObjectAnimator.ofFloat(this, "curScale", 1f, bigScale)
    }
    private val scroller = OverScroller(context)
    private val flingRunner = HenFlingRunner()
    private val gestureDetect = GestureDetectorCompat(context, HenGestureListener())
    private val scaleGestureDetect = ScaleGestureDetector(context, HenScaleGestureListener())

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        originOffsetX = (width - AVATAR_WIDTH) / 2f
        originOffsetY = (height - AVATAR_WIDTH) / 2f
        if (avatar.width / avatar.height.toFloat() > width / height.toFloat()) {
            bigScale = height / avatar.height.toFloat()
        } else {
            bigScale = width / avatar.width.toFloat()
        }
        curScale = 1f
        scaleAnimator.setFloatValues(1f, bigScale)
    }

    override fun onDraw(canvas: Canvas) {
        val scale = (curScale - 1) / (bigScale - 1)
        canvas.translate(offsetX * scale, offsetY * scale)
        canvas.scale(curScale, curScale, width / 2f, height / 2f)
        canvas.drawBitmap(avatar, originOffsetX, originOffsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetect.onTouchEvent(event)
        if (!scaleGestureDetect.isInProgress) {
            gestureDetect.onTouchEvent((event))
        }
        return true
    }

    private fun fixOffset() {
        val maxOffsetX = (avatar.width * bigScale - width) / 2
        val maxOffsetY = (avatar.height * bigScale - height) / 2
        offsetX = offsetX.coerceAtLeast(-maxOffsetX).coerceAtMost(maxOffsetX)
        offsetY = offsetY.coerceAtLeast(-maxOffsetY).coerceAtMost(maxOffsetY)
    }

    inner class HenGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (big) {
                val maxOffsetX = ((avatar.width * bigScale - width) / 2).toInt()
                val maxOffsetY = ((avatar.height * bigScale - height) / 2).toInt()
                scroller.fling(
                    offsetX.toInt(),
                    offsetY.toInt(),
                    velocityX.toInt(),
                    velocityY.toInt(),
                    -maxOffsetX,
                    maxOffsetX,
                    -maxOffsetY,
                    maxOffsetY,
                    40.dp2px.toInt(),
                    40.dp2px.toInt()
                )
                postOnAnimation(flingRunner)
            }
            return false
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            big = !big
            if (big) {
                offsetX = (e.x - width / 2) * (1 - bigScale)
                offsetY = (e.y - height / 2) * (1 - bigScale)
                fixOffset()
                scaleAnimator.start()
            } else {
                scaleAnimator.reverse()
            }
            return false
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            if (big) {
                offsetX -= distanceX
                offsetY -= distanceY
                fixOffset()
                invalidate()
            }
            return false
        }
    }

    inner class HenFlingRunner : Runnable {
        override fun run() {
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.currX.toFloat()
                offsetY = scroller.currY.toFloat()
                invalidate()
                postOnAnimation(this)
            }
        }
    }

    inner class HenScaleGestureListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val temp = curScale * detector.scaleFactor
            if (temp < 1 || temp > bigScale) {
                return false
            }
            curScale *= detector.scaleFactor
            return true
        }

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            offsetX = (detector.focusX - width / 2f) * (1 - bigScale)
            offsetY = (detector.focusY - height / 2f) * (1 - bigScale)
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {

        }
    }

}