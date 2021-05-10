package com.osung.ablytest.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.osung.ablytest.databinding.HomeFragmentBinding
import com.osung.ablytest.domain.entity.Goods
import com.osung.ablytest.presentation.adapter.HomeAdapter
import com.osung.ablytest.presentation.adapter.util.HomeDecoration
import com.osung.ablytest.presentation.viewmodel.HomeViewModel
import com.osung.ablytest.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), HomeAdapter.ItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private val mainViewModel: MainViewModel by sharedViewModel()
    private val homeViewModel: HomeViewModel by viewModel()

    private val mAdapter by lazy { HomeAdapter(this) }
    private lateinit var mBinding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = HomeFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = homeViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.homeList.adapter = mAdapter
        mBinding.homeList.addItemDecoration(HomeDecoration(requireContext()))
        mBinding.homeList.layoutManager = GridLayoutManager(requireContext(), 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if(mAdapter.getItemViewType(position) == HomeAdapter.typeHeader) 2 else 1
                }
            }
        }

        mBinding.swipeRefresh.setOnRefreshListener(this)

        homeViewModel.bannerList.observe(viewLifecycleOwner, {
            mBinding.swipeRefresh.isRefreshing = false
        })

        homeViewModel.eventAutoScroll.observe(viewLifecycleOwner, {
            mAdapter.changeNextBanner()
        })
    }

    override fun setGoodsZzim(item: Goods, isSelected: Boolean) {
        mainViewModel.run {
            if(isSelected) addItemZzim(item) else removeItemZzim(item)
        }
    }

    override fun swipeUserBanner() {
        homeViewModel.swipeUserBanner()
    }

    override fun onRefresh() {
        homeViewModel.initItems()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}