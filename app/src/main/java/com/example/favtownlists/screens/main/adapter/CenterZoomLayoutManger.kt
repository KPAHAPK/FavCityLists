package com.example.favtownlists.screens.main.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


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
    private var mPendingTargetPos = -1
    private var mPendingPosOffset = -1

    var onItemSelectedListener: OnItemSelectedListener? = null

    interface OnItemSelectedListener {
        fun onItemSelected(position: Int)
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val orientation: Int = getOrientation()
        return if (orientation == HORIZONTAL) {
            val scrolled: Int = super.scrollHorizontallyBy(dx, recycler, state)
            val midpoint = getWidth() / 2f
            val d0 = 0f
            val d1 = mShrinkDistance * midpoint
            val s0 = 1f
            val s1 = 1f - mShrinkAmount
            for (i in 0 until getChildCount()) {
                val child: View = getChildAt(i)!!
                val childMidpoint: Float = (getDecoratedRight(child) + getDecoratedLeft(child)) / 2f
                val d = Math.min(d1, Math.abs(midpoint - childMidpoint))
                val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                child.setScaleX(scale)
                child.setScaleY(scale)
            }
            scrolled
        } else {
            0
        }
    }

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
                    var childDistanceFromCenter = Math.abs(childCenterX - recyclerViewCenterX)
                    if (childDistanceFromCenter < minDistance) {
                        minDistance = childDistanceFromCenter
                        position = getPosition(child)
                    }
                }
                onItemSelectedListener?.onItemSelected(position)
            }
        }
    }

    //    override fun onScrollStateChanged(state: Int) {
//        super.onScrollStateChanged(state)
//        if (state == RecyclerView.SCROLL_STATE_IDLE) {
//            if (onScrollStopListener != null) {
//                var selected = 0
//                var lastHeight = 0f
//                for (i in 0 until childCount) {
//                    val child = getChildAt(i) ?: continue
//                    if (lastHeight < child.scaleY) {
//                        lastHeight = child.scaleY
//                        selected = i
//                    }
//                }
//                onScrollStopListener?.selectedView(selected)
//            }
//        }
//    }
//
    var onScrollStopListener: OnScrollStopListener? = null

    interface OnScrollStopListener {
        fun selectedView(position: Int)
    }

    private fun getRecyclerViewCenterX(): Int {
        return (paddingRight - paddingLeft) / 2 + paddingLeft
    }

//    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
//        if (mPendingTargetPos != -1 && state.itemCount > 0) {
//            scrollToPositionWithOffset(mPendingTargetPos, mPendingPosOffset)
//            mPendingTargetPos = -1
//            mPendingPosOffset = -1
//        }
//        super.onLayoutChildren(recycler, state)
//    }
//
//    override fun onRestoreInstanceState(state: Parcelable?) {
//        mPendingTargetPos = -1
//        mPendingPosOffset = -1
//        super.onRestoreInstanceState(state)
//    }
//
//    /**
//     * Установить стартовую позицию.
//     * @param position
//     * @param offset
//     */
//    fun setTargetStartPos(position: Int, offset: Int) {
//        mPendingTargetPos = position
//        mPendingPosOffset = offset
//    }
}