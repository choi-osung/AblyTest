package com.osung.ablytest.data.remote

import com.osung.ablytest.data.remote.model.ResponseHeader
import com.osung.ablytest.data.remote.model.ResponseNextGoods
import io.reactivex.rxjava3.core.Single

interface RemoteDataSource {
    /**
     * 헤더 정보 요청
     *
     * @return
     */
    fun getHeader(): Single<ResponseHeader>

    /**
     * 다음 상품 페이지 요청
     *
     * @param lastId
     * @return
     */
    fun getNextGoods(lastId: Int) : Single<ResponseNextGoods>
}