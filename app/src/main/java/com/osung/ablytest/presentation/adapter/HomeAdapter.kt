package com.osung.ablytest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.osung.ablytest.R
import com.osung.ablytest.databinding.AdapterGoodsBinding
import com.osung.ablytest.databinding.AdapterHomeHeaderBinding
import com.osung.ablytest.domain.entity.Banner
import com.osung.ablytest.domain.entity.Goods
import com.osung.ablytest.presentation.adapter.util.GoodsDiff

class HomeAdapter(
    private val listener: ItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val bannerList = mutableListOf<Banner>()
    private val goodsList = mutableListOf<Goods>()

    private var bannerPager: ViewPager2? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == typeHeader) {
            BannerViewHolder(
                AdapterHomeHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            GoodsViewHolder(
                AdapterGoodsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemId(position: Int): Long {
        return if (position == 0) {
            super.getItemId(position)
        } else {
            goodsList[position - 1].id.toLong()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BannerViewHolder)
            holder.bind(bannerList)
        else if (holder is GoodsViewHolder) {
            holder.bind(goodsList[holder.adapterPosition - 1])
        }

    }

    fun setBannerList(items: List<Banner>) {
        bannerList.clear()
        bannerList.addAll(items)
        notifyItemChanged(0)
    }

    fun setGoodsList(items: List<Goods>) {
        val diff = GoodsDiff(goodsList, items)
        val diffResult = DiffUtil.calculateDiff(diff)

        goodsList.clear()
        goodsList.addAll(items)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return goodsList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) typeHeader else typeGoods
    }

    /**
     * 배너 자동 스크롤 이벤트 처리
     *
     */
    fun changeNextBanner() {
        bannerPager?.let { pager ->
            val pos = (pager.currentItem + 1).rem(bannerList.size)
            pager.setCurrentItem(pos, true)
        }
    }

    /**
     * 배너 ViewHolder
     *
     * @property mBinding
     */
    inner class BannerViewHolder(private val mBinding: AdapterHomeHeaderBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun bind(list: MutableList<Banner>) {
            with(mBinding.bannerContainer) {
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                adapter = BannerAdapter()

                //사용자의 배너 swipe 이벤트 처리
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        mBinding.bannerIndicator.text =
                            String.format(
                                mBinding.root.context.getString(R.string.banner_page_indicator),
                                position + 1,
                                list.size
                            )

                        listener.swipeUserBanner()
                    }
                })

                bannerPager = this
            }

            with(mBinding) {
                bannerList = list
                bannerIndicator.text =
                    String.format(
                        mBinding.root.context.getString(R.string.banner_page_indicator),
                        bannerContainer.currentItem + 1,
                        list.size
                    )
            }
        }
    }

    /**
     * 상품 ViewHolder
     *
     * @property mBinding
     */
    inner class GoodsViewHolder(private val mBinding: AdapterGoodsBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun bind(item: Goods) {
            with(mBinding) {
                this.item = item

                goodsLike.setOnCheckedChangeListener { _, isChecked ->
                    goodsLike.isChecked = isChecked
                    listener.setGoodsZzim(item, isChecked)
                }
            }
        }
    }

    interface ItemClickListener {
        fun setGoodsZzim(item: Goods, isSelected: Boolean)
        fun swipeUserBanner()
    }

    companion object {
        const val typeHeader = 0
        const val typeGoods = 1
    }
}