package com.osung.ablytest.data.remote.model

import com.google.gson.annotations.SerializedName

data class ResponseBanner(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String
)