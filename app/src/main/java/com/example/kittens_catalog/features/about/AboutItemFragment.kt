package com.example.kittens_catalog.features.about

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentAboutItemListBinding
import com.example.kittens_catalog.features.base.BaseFragment
import com.example.kittens_catalog.features.kitten_list.KittenListFragmentArgs
import com.example.kittens_catalog.features.kitten_list.KittenListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutItemFragment : BaseFragment<FragmentAboutItemListBinding>(R.layout.fragment_about_item_list) {

    override val binding: FragmentAboutItemListBinding by viewBinding()
    private val aboutAdapter by lazy {
        AboutItemAdapter()
    }

    private val viewModel: KittenListViewModel by viewModels()
    private val args: KittenListFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.list.layoutManager = layoutManager
        binding.list.adapter = aboutAdapter
        val authors: List<AboutInfo> = listOf(
            AboutInfo(name = "Алексеенко Борис"),
            AboutInfo(name = "Отмахова Мария"),
            AboutInfo(name = "Хафаева Наиля")
        )
        aboutAdapter.setData(authors)
    }
}