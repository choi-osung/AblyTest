package com.osung.ablytest

import android.app.Application
import com.osung.ablytest.data.local.room.AblyDatabase
import com.osung.ablytest.presentation.util.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        AblyDatabase.getDatabase(this)

        startKoin {
            androidContext(this@App)
            modules(viewModelModule, networkModule, repositoryModule, dataSourceModule, databaseModule)
        }
    }
}