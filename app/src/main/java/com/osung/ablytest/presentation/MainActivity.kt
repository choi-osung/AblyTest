package com.osung.ablytest.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.osung.ablytest.R
import com.osung.ablytest.databinding.ActivityMainBinding
import com.osung.ablytest.presentation.view.HomeFragment
import com.osung.ablytest.presentation.view.ZzimFragment
import com.osung.ablytest.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mBinding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private val mViewModel: MainViewModel by viewModel()

    private val tabTitle by lazy { resources.getStringArray(R.array.page_name) }
    private val tabImage by lazy { resources.obtainTypedArray(R.array.page_image) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        initToolbar()

        mBinding.pageContainer.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(mBinding.tab, mBinding.pageContainer) { tab, position ->
            tab.text = tabTitle[position]
            tab.icon = tabImage.getDrawable(position)
        }.attach()

        mBinding.pageContainer.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mBinding.title = tabTitle[position]
            }
        })

        mBinding.pageContainer.isUserInputEnabled = false

        mViewModel.zzimList.observe(this, {})
    }

    private fun initToolbar() {
        with(mBinding.toolbar) {
            setSupportActionBar(this)
            setContentInsetsAbsolute(0,0)
        }

        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private inner class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa){
        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> HomeFragment.newInstance()
                else -> ZzimFragment.newInstance()
            }
        }
        override fun getItemCount(): Int = tabTitle.size
    }


}