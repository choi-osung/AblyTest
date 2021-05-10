package com.osung.ablytest.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ZzimObject(
    @PrimaryKey val id: Int,
    val image: String,
    val name: String,
    val actualPrice: Int, //기본 가격
    val price: Int, //실제 가격
    val sellCount: Int, //구매 중
    val isNew: Boolean,
)