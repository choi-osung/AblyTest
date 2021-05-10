package com.osung.ablytest.presentation.custom

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.osung.ablytest.presentation.util.getGoodsImageHeight

/**
 * 상품 이미지의 비율에 따라 height를 width를 기준으로 조정.
 *
 * @param context
 * @param attrs
 * @param defStyleAttr
 */
class GoodsImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)

        layoutParams.apply {
            height = width.getGoodsImageHeight()
        }
    }
}