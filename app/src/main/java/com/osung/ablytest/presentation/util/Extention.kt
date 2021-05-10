package com.osung.ablytest.presentation.util

import android.content.Context
import android.util.TypedValue

fun Int.getGoodsImageHeight() = this

fun Int.getBannerImageHeight() = (this * 0.7).toInt()

fun Number.dpToPx(context: Context) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics).toInt()


