package com.example.favcitylists.screens.main.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs
import kotlin.math.min


class CenterZoomLayoutManager(
    context: Context?,
    orientation: Int = RecyclerView.HORIZONTAL,
    reverseLayout: Boolean = false
) :
    LinearLayoutManager(
        context,
        orientation,
        reverseLayout
    ) {

    private val mShrinkAmount = 0.5f
    private val mShrinkDistance = 0.5f

    var onItemSelectedListener: OnItemSelectedListener? = null

    interface OnItemSelectedListener {
        fun onItemSelected(position: Int)
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val orientation: Int = orientation
        return if (orientation == HORIZONTAL) {
            val scrolled: Int = super.scrollHorizontallyBy(dx, recycler, state)
            val midpoint = width / 2f
            val d0 = 0f
            val d1 = mShrinkDistance * midpoint
            val s0 = 1f
            val s1 = 1f - mShrinkAmount
            for (i in 0 until childCount) {
                val child: View = getChildAt(i)!!
                val childMidpoint: Float = (getDecoratedRight(child) + getDecoratedLeft(child)) / 2f
                val d = min(d1, abs(midpoint - childMidpoint))
                val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                child.scaleX = scale
                child.scaleY = scale
            }
            scrolled
        } else {
            0
        }
    }

    /**
     * Определение позиции элемента находящегося в центре экрана.
     */
    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            if (onItemSelectedListener != null) {
                val recyclerViewCenterX = getRecyclerViewCenterX()
                var minDistance = width
                var position = -1
                for (i in 0 until childCount) {
                    val child: View = getChildAt(i) ?: continue
                    val childCenterX =
                        getDecoratedLeft(child) + (getDecoratedRight(child) - getDecoratedLeft(child)) / 2
                    val childDistanceFromCenter = abs(childCenterX - recyclerViewCenterX)
                    if (childDistanceFromCenter < minDistance) {
                        minDistance = childDistanceFromCenter
                        position = getPosition(child)
                    }
                }
                onItemSelectedListener?.onItemSelected(position)
            }
        }
    }

    private fun getRecyclerViewCenterX(): Int {
        return (paddingRight - paddingLeft) / 2 + paddingLeft
    }
}