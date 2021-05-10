package com.osung.ablytest.domain.repository

import androidx.lifecycle.LiveData
import com.osung.ablytest.domain.entity.Banner
import com.osung.ablytest.domain.entity.Goods
import io.reactivex.rxjava3.core.Single

interface HomeRepository {
    /**
     * 첫 헤더 정보(배너, 초기 상품) 요청
     *
     * @return
     */
    fun getHeader() : Single<Pair<List<Banner>, List<Goods>>>

    /**
     * 다음 상품 요청
     *
     * @param lastId 마지막 상품 id
     * @return
     */
    fun getNextGoods(lastId: Int) : Single<List<Goods>>

    /**
     * 좋아요 리스트 관찰자 요청.
     *
     * @return
     */
    fun getZzimListChangeListener() : LiveData<List<Goods>>

    /**
     * 좋아요 등록.
     *
     * @param item 등록할 아이템.
     * @return
     */
    fun addItemZzim(item: Goods) : Single<Long>

    /**
     * 좋아요 해제.
     *
     * @param item 해제할 아이템.
     * @return
     */
    fun removeItemZzim(item: Goods) : Single<Int>
}