package com.osung.ablytest.data.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.osung.ablytest.data.local.model.ZzimObject

@Dao
interface ZzimDao {
    @Query("SELECT * FROM ZzimObject")
    fun getZzimListChangeListener() : LiveData<List<ZzimObject>>

    @Query("SELECT * FROM ZzimObject")
    fun getZzimList() : List<ZzimObject>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItemZzim(item: ZzimObject) : Long

    @Delete
    fun deleteItemZzim(item: ZzimObject) : Int
}