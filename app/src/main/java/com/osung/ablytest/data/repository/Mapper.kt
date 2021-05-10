package com.osung.ablytest.data.repository

import com.osung.ablytest.data.local.model.ZzimObject
import com.osung.ablytest.data.remote.model.ResponseBanner
import com.osung.ablytest.data.remote.model.ResponseHeader
import com.osung.ablytest.data.remote.model.ResponseGoods
import com.osung.ablytest.domain.entity.Banner
import com.osung.ablytest.domain.entity.Goods

/**
 * Mapper Class
 */

fun ResponseBanner.entity() = Banner(id, image)
fun ResponseGoods.entity() = Goods(id, image, name, actualPrice, price, sellCount, isNew)
fun ZzimObject.entity() = Goods(id, image, name, actualPrice, price, sellCount, isNew)

fun ResponseHeader.mapper() : Pair<List<Banner>, List<Goods>> {
    val first = banners.map { it.entity() }
    val second = goods.map { it.entity() }
    return Pair(first, second)
}

fun Goods.getZzimObject() = ZzimObject(id, image, name, actualPrice, price, sellCount, isNew)