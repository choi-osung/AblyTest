package com.osung.ablytest.presentation.util

import com.osung.ablytest.data.local.LocalDataSource
import com.osung.ablytest.data.local.LocalDataSourceImpl
import com.osung.ablytest.data.local.room.AblyDatabase
import com.osung.ablytest.data.remote.AblyApi
import com.osung.ablytest.data.remote.RemoteDataSource
import com.osung.ablytest.data.remote.RemoteDataSourceImpl
import com.osung.ablytest.domain.repository.HomeRepository
import com.osung.ablytest.data.repository.HomeRepositoryImpl
import com.osung.ablytest.presentation.viewmodel.HomeViewModel
import com.osung.ablytest.presentation.viewmodel.MainViewModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { MainViewModel(get()) }

    viewModel { HomeViewModel(get()) }
}

val repositoryModule = module {
    single<HomeRepository> { HomeRepositoryImpl(get(), get()) }
}

val dataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }

    single<LocalDataSource> { LocalDataSourceImpl(get()) }
}

val databaseModule = module {
    single { AblyDatabase.getDatabase(androidContext()) }
}

val networkModule = module {
    single { GsonConverterFactory.create() as Converter.Factory }
    single { RxJava3CallAdapterFactory.create() as CallAdapter.Factory }

    single {
        Retrofit.Builder()
            .addConverterFactory(get())
            .baseUrl(AblyApi.baseUrl)
            .addCallAdapterFactory(get())
            .build()
            .create(AblyApi::class.java)
    }
}