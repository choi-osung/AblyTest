package com.osung.ablytest.data.remote.model

import com.google.gson.annotations.SerializedName

data class ResponseGoods(
    @SerializedName("actual_price")
    val actualPrice: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("sell_count")
    val sellCount: Int
)

