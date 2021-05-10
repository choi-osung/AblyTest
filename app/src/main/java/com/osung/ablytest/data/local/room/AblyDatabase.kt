package com.osung.ablytest.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.osung.ablytest.data.local.model.ZzimObject
import com.osung.ablytest.data.local.room.dao.ZzimDao

@Database(entities = [ZzimObject::class], version = 1)
abstract class AblyDatabase: RoomDatabase() {
    abstract fun zzimDao() : ZzimDao

    companion object {
        @Volatile
        private var INSTANCE: AblyDatabase? = null

        fun getDatabase(context: Context): AblyDatabase {

            if(INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AblyDatabase::class.java,
                        "ably_database"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}