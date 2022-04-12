package com.example.wyqrelearn.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.math.max

class TabLayout : ViewGroup {
    private val childBounds = arrayListOf<Rect>()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var lineMaxHeight = 0
        var maxWidth = 0
        var widthUsed = 0
        var heightUsed = 0
        val parentWidthSize = MeasureSpec.getSize(widthMeasureSpec)
        val parentWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        for ((index, child) in children.withIndex()) {
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            if (parentWidthMode != MeasureSpec.UNSPECIFIED && child.measuredWidth + widthUsed >= parentWidthSize) {
                widthUsed = 0
                heightUsed += lineMaxHeight
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
                lineMaxHeight = 0
            }
            if (index >= childBounds.size) {
                childBounds.add(Rect())
            }
            childBounds[index].set(widthUsed, heightUsed, widthUsed + child.measuredWidth, heightUsed + child.measuredHeight)
            widthUsed += child.measuredWidth
            maxWidth = max(maxWidth, widthUsed)
            lineMaxHeight = max(lineMaxHeight, child.measuredHeight)
        }
        setMeasuredDimension(maxWidth, heightUsed + lineMaxHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for ((index, child) in children.withIndex()) {
            val bound = childBounds[index]
            child.layout(bound.left, bound.top, bound.right, bound.bottom,)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}