package com.example.kittens_catalog.features.kitten_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kittens_catalog.databinding.FragmentKittenListBinding
import com.example.kittens_catalog.features.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KittenListFragment : BaseFragment<FragmentKittenListBinding>() {
    private val kittenAdapter by lazy {
        KittenListAdapter()
    }

    private val viewModel: KittenListViewModel by viewModels()


    override fun setupViewBinding(inflater: LayoutInflater): FragmentKittenListBinding {
        return FragmentKittenListBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseViewModel.doRefresh()
        initView()
        subscribeUi()
    }

    fun initView() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.kittenList.layoutManager = layoutManager
        binding.kittenList.adapter = kittenAdapter
    }

    fun subscribeUi() {
        viewModel.getInitialData(null, null, null, null).observe(viewLifecycleOwner) {
            kittenAdapter.setData(it)
            println(viewModel.breeds)
        }
        binding.kittenList.adapter = kittenAdapter
    }
}