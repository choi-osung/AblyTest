package com.osung.ablytest.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.osung.ablytest.data.local.LocalDataSource
import com.osung.ablytest.data.remote.RemoteDataSource
import com.osung.ablytest.domain.entity.Banner
import com.osung.ablytest.domain.entity.Goods
import com.osung.ablytest.domain.repository.HomeRepository
import io.reactivex.rxjava3.core.Single

class HomeRepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) : HomeRepository {

    override fun getHeader(): Single<Pair<List<Banner>, List<Goods>>> {
        val zzimObservable = local.getZzimList().map { list ->
            list.map { it.entity() }
        }
        val headerObservable = remote.getHeader().map {
            it.mapper()
        }

        //좋아요로 등록된 아이템 상태 변경.
        return Single.zip(zzimObservable, headerObservable, { zzim, header ->
            header.second.filter { goods -> zzim.contains(goods) }
                .map { it.isZzim = true }

            header
        })
    }

    override fun getNextGoods(lastId: Int): Single<List<Goods>> {
        val nextGoodsObservable = remote.getNextGoods(lastId).map { response ->
            response.goods.map { it.entity() }
        }

        val zzimObservable = local.getZzimList().map { list ->
            list.map { it.entity() }
        }

        //좋아요로 등록된 아이템 상태 변경.
        return Single.zip(zzimObservable, nextGoodsObservable, { zzim, goods ->
            goods.filter { zzim.contains(it) }
                .map { it.isZzim = true }

            goods
        })
    }

    override fun getZzimListChangeListener(): LiveData<List<Goods>> {
        return Transformations.switchMap(local.getZzimListChangeListener()) { list ->
            MutableLiveData<List<Goods>>().apply {
                value = list.map { it.entity() }
            }
        }
    }

    override fun addItemZzim(item: Goods): Single<Long> {
        val zzim = item.getZzimObject()
        return local.addItemZzim(zzim)
    }

    override fun removeItemZzim(item: Goods): Single<Int> {
        val zzim = item.getZzimObject()
        return local.removeItemZzim(zzim)
    }


}