package com.osung.ablytest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.osung.ablytest.domain.entity.Banner
import com.osung.ablytest.domain.entity.Goods
import com.osung.ablytest.domain.repository.HomeRepository
import com.osung.ablytest.presentation.util.SingleLiveEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

class HomeViewModel(
    private val repository: HomeRepository
): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    //배너 리스트 LiveData
    private val _bannerList = MutableLiveData<List<Banner>>().apply { value = listOf() }
    val bannerList: LiveData<List<Banner>> = _bannerList

    //상품 리스트 LiveData
    private val _goodsList = MutableLiveData<List<Goods>>().apply { value = listOf() }
    val goodsList: LiveData<List<Goods>> = _goodsList

    //배너 자동 스크롤 이벤트
    private val _eventAutoScroll = SingleLiveEvent<Any>()
    val eventAutoScroll: LiveData<Any> = _eventAutoScroll

    //배너 자동 스크롤 타이머
    private var timer: Disposable? = null

    //페이징 상태 여부
    private var hasNextPage = true

    init {
        initItems()
    }

    /**
     * 초기 아이템(배너, 상품)을 요청.
     * 배너 자동 스크롤 타이머 구동.
     */
    fun initItems() {
        timer?.dispose()

        val disposable = repository.getHeader().subscribe({
            it.first.also { list -> _bannerList.value = list }
            it.second.also { list -> _goodsList.value = list }

            setBannerAutoScroll()

            hasNextPage = true
        }, {})

        compositeDisposable.add(disposable)
    }

    /**
     * 사용자가 배너를 수동으로 swipe 할 경우 이벤트.
     * 타이머 초기화
     */
    fun swipeUserBanner() {
        timer?.dispose()
        setBannerAutoScroll()
    }

    /**
     * 타이머 세팅
     * interval - 5s
     */
    private fun setBannerAutoScroll() {
        timer = Observable.interval(timerDelay, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _eventAutoScroll.call()
            }

        compositeDisposable.add(timer)
    }

    /**
     * 리스트 스크롤 이벤트
     */
    val goodsScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            _goodsList.value?.let { items ->
                val lastPosition = (recyclerView.layoutManager as GridLayoutManager)
                    .findLastVisibleItemPosition()
                val itemCount = items.size

                //페이징 처리
                if (lastPosition == itemCount && hasNextPage && items.isNotEmpty()) {
                    repository.getNextGoods(items.last().id).subscribe({ list ->
                        if (list.isEmpty()) hasNextPage = false
                        else {
                            //기존의 상품에 새로운 상품 리스트를 합친다.
                            _goodsList.value = mutableListOf<Goods>().also {
                                it.addAll(items)
                                it.addAll(list)
                            }
                        }
                    }, {
                        it.message
                    })
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }

    companion object {
        const val timerDelay = 5000L
    }
}