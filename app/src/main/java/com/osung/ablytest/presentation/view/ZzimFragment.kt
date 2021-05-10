package com.osung.ablytest.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.osung.ablytest.databinding.ZzimFragmentBinding
import com.osung.ablytest.presentation.adapter.ZzimAdapter
import com.osung.ablytest.presentation.adapter.util.ZzimDecoration
import com.osung.ablytest.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ZzimFragment : Fragment() {

    private val mViewModel: MainViewModel by sharedViewModel()
    private val mAdapter by lazy { ZzimAdapter() }
    private lateinit var mBinding: ZzimFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = ZzimFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = mViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(mBinding.zzimList) {
            this.adapter = mAdapter
            addItemDecoration(ZzimDecoration(requireContext()))
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

    }

    companion object {
        fun newInstance() = ZzimFragment()
    }
}
