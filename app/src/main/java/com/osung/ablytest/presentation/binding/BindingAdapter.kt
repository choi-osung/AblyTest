package com.osung.ablytest.presentation.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.osung.ablytest.R
import com.osung.ablytest.domain.entity.Banner
import com.osung.ablytest.domain.entity.Goods
import com.osung.ablytest.presentation.adapter.BannerAdapter
import com.osung.ablytest.presentation.adapter.HomeAdapter
import com.osung.ablytest.presentation.adapter.ZzimAdapter
import com.osung.ablytest.presentation.util.dpToPx

object BindingAdapter {
    /**
     * 배너 정보 전달.
     *
     * @param recyclerView
     * @param items 배너 리스트
     */
    @JvmStatic
    @BindingAdapter("app:setHeader")
    fun setHeader(recyclerView: RecyclerView, items: List<Banner>) {
        ((recyclerView.adapter) as HomeAdapter).run {
            setBannerList(items)
        }
    }

    /**
     * 배너 정보 전달.
     *
     * @param viewPager
     * @param items 배너 리스트
     */
    @JvmStatic
    @BindingAdapter("app:setBanner")
    fun setBanner(viewPager: ViewPager2, items: List<Banner>) {
        ((viewPager.adapter) as BannerAdapter).run {
            setBannerList(items)
        }
    }

    /**
     * 리스트 Adapter에 상품 정보 전달.
     *
     * @param recyclerView
     * @param items 상품 리스트
     */
    @JvmStatic
    @BindingAdapter("app:setGoods")
    fun setGoods(recyclerView: RecyclerView, items: List<Goods>) {
        ((recyclerView.adapter) as? HomeAdapter)?.setGoodsList(items)

        (recyclerView.adapter as? ZzimAdapter)?.setZzimList(items)
    }

    /**
     * 할인율 표기.
     * 판매 가격과 동일할 경우 뷰를 감춘다.
     *
     * @param view
     * @param actualPrice 상품 가격
     * @param price 판매 가격
     */
    @JvmStatic
    @BindingAdapter("app:setActualPrice", "app:setPrice")
    fun setDiscount(view: TextView, actualPrice: Int, price: Int) {
        if(actualPrice == price) {
            view.visibility = View.GONE
        }else {
            view.text = String.format(view.context.getString(R.string.goods_discount), price * 100 / actualPrice)
        }
    }

    /**
     * 이미지 로드
     *
     * @param view
     * @param url 이미지 주소
     */
    @JvmStatic
    @BindingAdapter("app:setImage")
    fun setImage(view: ImageView, url: String?) {
        url?.let {
            Glide.with(view)
                .load(it)
                .override(view.width,view.height)
                .transform(RoundedCorners(4.dpToPx(view.context)))
                .into(view)
        }
    }

    /**
     * 스크롤 이벤트 리스너 등록
     *
     * @param recyclerView
     * @param listener
     */
    @JvmStatic
    @BindingAdapter("app:setScrollListener")
    fun setScrollListener(recyclerView: RecyclerView, listener: RecyclerView.OnScrollListener) {
        recyclerView.addOnScrollListener(listener)
    }
}