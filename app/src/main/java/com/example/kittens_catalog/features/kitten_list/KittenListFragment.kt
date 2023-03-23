package com.example.kittens_catalog.features.kitten_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentKittenListBinding
import com.example.kittens_catalog.features.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext.SupertypesPolicy.None


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
        viewModel.init()

        binding.breeds.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), binding.breeds)
            viewModel.breeds.value?.map {
                popupMenu.menu.add(0, 0, 0, it)
            }
            popupMenu.menuInflater.inflate(R.menu.pop_up_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem -> // Toast message on menu item clicked
                true
            }
            popupMenu.show()
        }

        kittenAdapter.clickListener = { kittenItem -> navigateToKitten(kittenItem.id) }
        binding.searchAssets.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                if(newText == ""){
                    this.onQueryTextSubmit("");
                }
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(null, null, query, null).observe(viewLifecycleOwner) {
                    kittenAdapter.setData(it)
                }
                return false
            }

        })
    }

    fun subscribeUi() {
        viewModel.search(null, null, null, null).observe(viewLifecycleOwner) {
            kittenAdapter.setData(it)
        }
        binding.kittenList.adapter = kittenAdapter
    }

    private fun navigateToKitten(id: Int) {
       navigate(R.id.kittenListFragment, KittenListFragmentDirections.actionKittenListFragmentToKittenFragment(id))
    }
}