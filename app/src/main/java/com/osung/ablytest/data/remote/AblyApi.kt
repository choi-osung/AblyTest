package com.osung.ablytest.data.remote

import com.osung.ablytest.data.remote.model.ResponseHeader
import com.osung.ablytest.data.remote.model.ResponseNextGoods
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AblyApi {

    @GET("/api/home")
    fun getHeader() : Single<ResponseHeader>

    @GET("/api/home/goods")
    fun getNextGoods(@Query("lastId") lastId: Int) : Single<ResponseNextGoods>

    companion object {
        const val baseUrl = "https://d2bab9i9pr8lds.cloudfront.net"
    }
}