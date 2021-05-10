package com.osung.ablytest.data.local

import androidx.lifecycle.LiveData
import com.osung.ablytest.data.local.model.ZzimObject
import com.osung.ablytest.data.local.room.AblyDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class LocalDataSourceImpl(
    private val database: AblyDatabase
) : LocalDataSource {

    override fun getZzimListChangeListener() : LiveData<List<ZzimObject>> {
        return database.zzimDao().getZzimListChangeListener()

    }

    override fun getZzimList(): Single<List<ZzimObject>> {
        return Single.fromCallable { database.zzimDao().getZzimList() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addItemZzim(item: ZzimObject) : Single<Long> {
        return Single.fromCallable { database.zzimDao().insertItemZzim(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun removeItemZzim(item: ZzimObject): Single<Int> {
        return Single.fromCallable { database.zzimDao().deleteItemZzim(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}