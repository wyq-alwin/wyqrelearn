package com.example.wyqrelearn.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import com.example.wyqrelearn.R
import com.example.wyqrelearn.dp2px
import com.example.wyqrelearn.sp2px

private val TEXT_SIZE = 12.sp2px
private val TEXT_MARGIN = 8.dp2px
private val HORIZONTAL_OFFSET = 5.dp2px
private val VERTICAL_OFFSET = 20.dp2px
private val EXTRA_VERTICAL_OFFSET = 20.dp2px

class MaterialEditText:
    androidx.appcompat.widget.AppCompatEditText{

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = TEXT_SIZE
    }

    private var floatingLabelShown = false

    var floatingLabelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    var useFloatingLabel = true
        set(value) {
            if (value == field) {
                return
            }
            field = value
            if (field) {
                setPadding(
                    paddingLeft, paddingTop + TEXT_SIZE.toInt() + TEXT_MARGIN.toInt(),
                    paddingRight, paddingBottom
                )
            } else {
                setPadding(
                    paddingLeft, paddingTop - TEXT_SIZE.toInt() - TEXT_MARGIN.toInt(),
                    paddingRight, paddingBottom
                )
            }

        }

    private val animator1 by lazy {
        ObjectAnimator.ofFloat(this, "floatingLabelFraction", 1f, 0f)
            .setDuration(500)
    }

    init {
        if (useFloatingLabel) {
            setPadding(
                paddingLeft, paddingTop + TEXT_SIZE.toInt() + TEXT_MARGIN.toInt(),
                paddingRight, paddingBottom
            )
        }
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        handleAttrs(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        handleAttrs(context, attrs)
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        if (floatingLabelShown && text.isNullOrEmpty()) {
            floatingLabelShown = false
            animator1.start()
        } else if (!floatingLabelShown && !text.isNullOrEmpty()) {
            floatingLabelShown = true
            animator1.reverse()
        }
    }

    private fun handleAttrs(context: Context, attrs: AttributeSet?) {
        context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText).let {
            useFloatingLabel = it.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true)
            it.recycle()
        }
        context.obtainStyledAttributes(attrs, intArrayOf(R.attr.useFloatingLabel)).let {
            useFloatingLabel = it.getBoolean(0, true)
            it.recycle()
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.alpha = (floatingLabelFraction * 0X8F).toInt()
        canvas?.drawText(
            hint.toString(),
            HORIZONTAL_OFFSET,
            VERTICAL_OFFSET + EXTRA_VERTICAL_OFFSET * (1 - floatingLabelFraction), paint
        )
    }

}