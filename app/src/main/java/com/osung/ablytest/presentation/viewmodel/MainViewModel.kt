package com.osung.ablytest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.osung.ablytest.domain.entity.Goods
import com.osung.ablytest.domain.repository.HomeRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainViewModel(
    private val repository: HomeRepository
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    //좋아요 상태 관찰
    var zzimList: LiveData<List<Goods>> = repository.getZzimListChangeListener()

    /**
     * 좋아요 등록
     *
     * @param item 등록할 아이템
     */
    fun addItemZzim(item: Goods) {
        val disposable = repository.addItemZzim(item)
            .subscribe()

        compositeDisposable.add(disposable)
    }

    /**
     * 좋아요 해제
     *
     * @param item 해제할 아이템
     */
    fun removeItemZzim(item: Goods) {
        val disposable = repository.removeItemZzim(item)
            .subscribe()

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }
}