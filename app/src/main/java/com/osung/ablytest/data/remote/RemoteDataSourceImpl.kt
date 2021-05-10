package com.osung.ablytest.data.remote

import com.osung.ablytest.data.remote.model.ResponseHeader
import com.osung.ablytest.data.remote.model.ResponseNextGoods
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RemoteDataSourceImpl(
    private val api: AblyApi
) : RemoteDataSource {
    override fun getHeader(): Single<ResponseHeader> {
        return api.getHeader()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getNextGoods(lastId: Int): Single<ResponseNextGoods> {
        return api.getNextGoods(lastId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}