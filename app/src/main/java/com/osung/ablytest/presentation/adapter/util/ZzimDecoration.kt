package com.osung.ablytest.presentation.adapter.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.osung.ablytest.presentation.util.dpToPx


class ZzimDecoration(context: Context): RecyclerView.ItemDecoration() {

    private val size21 = 21.dpToPx(context)
    private val size12 = 12.dpToPx(context)
    private val size6 = 6.dpToPx(context)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)

        val lp = view.layoutParams as GridLayoutManager.LayoutParams
        val spanIndex = lp.spanIndex

        with(outRect) {
            if(position == 0 || position == 1) {
                top = size12
                bottom = size21
            }else {
                bottom = size21
            }

            if(spanIndex == 0) {
                left = size12
                right = size6
            }else if(spanIndex == 1) {
                left = size6
                right = size12
            }
        }
    }
}