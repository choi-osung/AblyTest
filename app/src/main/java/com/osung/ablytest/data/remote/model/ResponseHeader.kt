package com.osung.ablytest.data.remote.model

import com.google.gson.annotations.SerializedName


data class ResponseHeader(
    @SerializedName("banners")
    val banners: List<ResponseBanner>,
    @SerializedName("goods")
    val goods: List<ResponseGoods>
)





