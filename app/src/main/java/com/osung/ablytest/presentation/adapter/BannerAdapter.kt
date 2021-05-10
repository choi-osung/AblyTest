package com.osung.ablytest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osung.ablytest.domain.entity.Banner
import com.osung.ablytest.databinding.AdapterBannerBinding

class BannerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val bannerList = mutableListOf<Banner>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BannerViewHolder(AdapterBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BannerViewHolder).bind(bannerList[position])
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }

    fun setBannerList(items: List<Banner>) {
        bannerList.clear()
        bannerList.addAll(items)
        notifyDataSetChanged()
    }

    inner class BannerViewHolder(private val mBinding: AdapterBannerBinding): RecyclerView.ViewHolder(mBinding.root) {
        fun bind(item: Banner) {
            mBinding.banner = item
        }
    }
}