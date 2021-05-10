package com.osung.ablytest.presentation.adapter.util

import androidx.recyclerview.widget.DiffUtil
import com.osung.ablytest.domain.entity.Goods

class GoodsDiff(
    private val oldItems: List<Goods>, // 현재 아이템
    private val newItems: List<Goods> // 새로운 아이템
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int =
        oldItems.size

    override fun getNewListSize(): Int =
        newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition].id
        val newItem = newItems[newItemPosition].id

        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return oldItem == newItem
    }
}