package com.example.kittens_catalog.features.kitten_list

import android.annotation.SuppressLint
import android.graphics.Path.Direction
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kittens_catalog.R
import com.example.kittens_catalog.data.network.models.BirthDate
import com.example.kittens_catalog.databinding.FragmentKittenListBinding
import com.example.kittens_catalog.features.base.BaseFragment
import com.google.android.material.slider.RangeSlider
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class KittenListFragment : BaseFragment<FragmentKittenListBinding>(R.layout.fragment_kitten_list) {
    override val binding: FragmentKittenListBinding by viewBinding()
    private val kittenAdapter by lazy {
        KittenListAdapter()
    }

    private val viewModel: KittenListViewModel by viewModels()
    private val args: KittenListFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseViewModel.doRefresh()
        initView()
        subscribeUi()
        initMenu()
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
            binding.addKittenButton.setOnClickListener {
                navigate(R.id.kittenListFragment, KittenListFragmentDirections.actionKittenListFragmentToKittenCreateFragment())
            }
        }

        binding.slider.valueFrom = 0f
        binding.slider.valueTo = 25f

        binding.yearPicker.setOnClickListener {
            binding.slider.visibility = if (binding.slider.isVisible) {
                viewModel.clearBirthDate()
                View.GONE
            } else View.VISIBLE
        }
        binding.slider.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: RangeSlider) {
                return
            }

            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: RangeSlider) {
                val lt = Calendar.getInstance().run {
                    add(Calendar.YEAR, -(slider.values[0].toInt()))
                    time
                }
                val gt = Calendar.getInstance().run {
                    add(Calendar.YEAR, -(slider.values[1].toInt()))
                    time
                }
                val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val bd = BirthDate(lt = formatter.format(lt), gt = formatter.format(gt))
                viewModel.setStates(null, null, null, bd)
                    .observe(viewLifecycleOwner) {
                        viewModel.search(it?.city, it?.breed, it?.name, it?.birthDate)
                            .observe(viewLifecycleOwner) { data ->
                                kittenAdapter.setData(data)
                            }
                    }
            }
        })


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

    private fun initMenu(){
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.home_button->{
                        findNavController().popBackStack(R.id.breeding_fragment,false)
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
    private fun navigateToKitten(id: Int) {
        navigate(
            R.id.kittenListFragment,
            KittenListFragmentDirections.actionKittenListFragmentToKittenFragment(id)
        )
    }
}