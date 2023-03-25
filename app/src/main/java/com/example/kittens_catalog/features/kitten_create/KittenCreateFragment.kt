package com.example.kittens_catalog.features.kitten_create

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentKittenCreateBinding
import com.example.kittens_catalog.features.base.BaseFragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class KittenCreateFragment : BaseFragment<FragmentKittenCreateBinding>(R.layout.fragment_kitten_create) {

    private val viewModel: KittenCreateViewModel by viewModels()
    override val binding: FragmentKittenCreateBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView() {
        initMenu()
        with(binding){
            kittenAge.setOnClickListener {
                val myFormat = "yyyy-MM-dd"
                val sdf = SimpleDateFormat(myFormat)
                val date = sdf.parse("2023-05-10")
                val timeInMillis = date.time
                val constraintBuilder = CalendarConstraints.Builder().setOpenAt(
                    timeInMillis
                ).build()
                val picker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setCalendarConstraints(constraintBuilder)
                    .build()
                picker.addOnPositiveButtonClickListener {
                    val date = Date(it)
                    val formattedDate = sdf.format(date)
                    kittenAge.text = formattedDate
                }
                picker.show(
                    requireActivity().supportFragmentManager,
                    "materialDatePicker"
                )
            }
        }
        binding.save.setOnClickListener {
            with(binding) {
                if(
                    kittenAge.text.isNullOrEmpty() ||
                    kittenBread.text.isNullOrEmpty() ||
                    kittenName.text.isNullOrEmpty() ||
                    kittenPrice.text.isNullOrEmpty() ||
                    kittenPicture.text.isNullOrEmpty()
                )
                    Toast.makeText(requireContext(), "You need to fill all required fields!", Toast.LENGTH_SHORT).show()
                else {
                    viewModel.createKitten(
                        about = kittenAbout.text.toString(),
                        birthDate = kittenAge.text.toString(),
                        breed = kittenBread.text.toString(),
                        city = kittenCity.text.toString(),
                        name = kittenName.text.toString(),
                        price = kittenPrice.text.toString().toInt(),
                        picture = kittenPicture.text.toString(),
                    )
                    Toast.makeText(requireContext(), "Kitten successfully created!", Toast.LENGTH_SHORT).show()
                    runBlocking {
                        findNavController().popBackStack()
                    }
                }
            }
        }
        binding.cancel.setOnClickListener {
            runBlocking {
                findNavController().popBackStack()
            }
        }
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
}
