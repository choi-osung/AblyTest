package com.osung.ablytest.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.osung.ablytest.databinding.AdapterGoodsBinding
import com.osung.ablytest.domain.entity.Goods
import com.osung.ablytest.presentation.adapter.util.GoodsDiff

class ZzimAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val zzimList = mutableListOf<Goods>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ZzimViewHolder(AdapterGoodsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ZzimViewHolder).bind(zzimList[position])
    }

    override fun getItemCount(): Int {
        return zzimList.size
    }

    fun setZzimList(items: List<Goods>) {
        val diff = GoodsDiff(zzimList, items)
        val diffResult = DiffUtil.calculateDiff(diff)

        zzimList.clear()
        zzimList.addAll(items)

        diffResult.dispatchUpdatesTo(this)
    }

    inner class ZzimViewHolder(private val mBinding: AdapterGoodsBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun bind(item: Goods) {
            mBinding.goodsLike.visibility = View.GONE
            mBinding.item = item
        }
    }
}