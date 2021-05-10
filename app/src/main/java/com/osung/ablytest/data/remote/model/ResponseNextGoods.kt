package com.osung.ablytest.data.remote.model

import com.google.gson.annotations.SerializedName

data class ResponseNextGoods(
    @SerializedName("goods")
    val goods: List<ResponseGoods>
)
