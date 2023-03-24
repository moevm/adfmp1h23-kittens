package com.example.kittens_catalog.features.kitten_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentKittenListBinding
import com.example.kittens_catalog.features.base.BaseFragment
import com.example.kittens_catalog.features.kitten.KittenFragmentArgs
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class KittenListFragment : BaseFragment<FragmentKittenListBinding>() {

    private val kittenAdapter by lazy {
        KittenListAdapter()
    }

    private val viewModel: KittenListViewModel by viewModels()
    private val args: KittenListFragmentArgs by navArgs()


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
        viewModel.setScreenType(args.type)
        viewModel.init()

        if (args.type) {
            binding.addKittenButton.isVisible = true
            // тут короче код для переход на страничку добавления котика
        }

        binding.breeds.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), binding.breeds)
            viewModel.breeds.value?.map {
                popupMenu.menu.add(0, 0, 0, it)
            }
            popupMenu.menu.add(0, 0, 0, "all")
            popupMenu.menuInflater.inflate(R.menu.pop_up_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem -> // Toast message on menu item clicked
                viewModel.setStates(
                    null,
                    null,
                    if (menuItem.title == "all") "" else menuItem.title as String,
                    null
                )
                    .observe(viewLifecycleOwner) { data ->
                        viewModel.search(data?.city, data?.breed, data?.name, data?.birthDate)
                            .observe(viewLifecycleOwner) {
                                kittenAdapter.setData(it)
                            }
                    }
                true
            }
            popupMenu.show()
        }

        kittenAdapter.clickListener = { kittenItem -> navigateToKitten(kittenItem.id) }
        binding.searchAssets.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText == "") {
                    this.onQueryTextSubmit("");
                }
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.setStates(query, null, null, null)
                    .observe(viewLifecycleOwner) {
                        viewModel.search(it?.city, it?.breed, it?.name, it?.birthDate)
                            .observe(viewLifecycleOwner) { data ->
                                kittenAdapter.setData(data)
                            }
                    }
                return false
            }

        })
    }

    fun subscribeUi() {
        with(viewModel) {
            val params = states.value
            search(params?.city, params?.breed, params?.name, params?.birthDate).observe(
                viewLifecycleOwner
            ) {
                kittenAdapter.setData(it)
            }
        }
        binding.kittenList.adapter = kittenAdapter
    }

    private fun navigateToKitten(id: Int) {
        navigate(
            R.id.kittenListFragment,
            KittenListFragmentDirections.actionKittenListFragmentToKittenFragment(id)
        )
    }
}