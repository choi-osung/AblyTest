package com.osung.ablytest.data.local

import androidx.lifecycle.LiveData
import com.osung.ablytest.data.local.model.ZzimObject
import io.reactivex.rxjava3.core.Single

interface LocalDataSource {
    /**
     * 좋아요 리스트 관찰자 요청.
     *
     * @return
     */
    fun getZzimListChangeListener() : LiveData<List<ZzimObject>>

    /**
     * 좋아요 리스트 요청.
     *
     * @return
     */
    fun getZzimList() : Single<List<ZzimObject>>

    /**
     * 좋아요 등록
     *
     * @param item 등록할 아이템.
     * @return
     */
    fun addItemZzim(item: ZzimObject) : Single<Long>

    /**
     * 좋아요 해제
     *
     * @param item 해제할 아이템.
     * @return
     */
    fun removeItemZzim(item: ZzimObject) : Single<Int>
}