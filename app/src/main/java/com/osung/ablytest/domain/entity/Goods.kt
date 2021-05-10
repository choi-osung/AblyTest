package com.osung.ablytest.domain.entity

/**
 * 상품 Entity
 *
 * @property id
 * @property image
 * @property name
 * @property actualPrice
 * @property price
 * @property sellCount
 * @property isNew
 * @property isZzim 좋아요 선택 여부
 */
data class Goods(
    val id: Int,
    val image: String,
    val name: String,
    val actualPrice: Int, //기본 가격
    val price: Int, //실제 가격
    val sellCount: Int, //구매 중
    val isNew: Boolean,
    var isZzim: Boolean = false
)

