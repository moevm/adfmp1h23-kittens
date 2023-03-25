package com.example.kittens_catalog.features.kitten

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentKittenBinding
import com.example.kittens_catalog.extensions.toDate
import com.example.kittens_catalog.features.base.BaseFragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class KittenFragment : BaseFragment<FragmentKittenBinding>(R.layout.fragment_kitten) {

    private val viewModel: KittenViewModel by viewModels()
    private val args: KittenFragmentArgs by navArgs()
    override val binding: FragmentKittenBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseViewModel.doRefresh()
        initView()
        setHasOptionsMenu(true)
    }

    fun initView() {
        initMenu()
        val id = args.id
        viewModel.getKitten(id).observe(viewLifecycleOwner) { kittenInfo->
            val isEditable = viewModel.kitten.value?.userId == baseViewModel.personalData.value?.id;

            kittenInfo?.also{info ->
                with(binding) {
                    kittenBread.isEnabled = isEditable
                    kittenBread.setText(info.breed)
                    kittenBread.addTextChangedListener {
                        if (!buttonSave.isEnabled) {
                            buttonSave.isEnabled = true
                        }
                    }
//                    }
                    kittenName.isEnabled = isEditable
                    kittenName.setText(info.name)
                    kittenName.addTextChangedListener {
                        if (!buttonSave.isEnabled) {
                            buttonSave.isEnabled = true
                        }
                    }
                    kittenAbout.isEnabled = isEditable
                    kittenAbout.setText(info.about)
                    kittenAbout.addTextChangedListener {
                        if (!buttonSave.isEnabled) {
                            buttonSave.isEnabled = true
                        }
                    }
                    kittenPrice.isEnabled = isEditable
                    kittenPrice.setText(info.price.toString())
                    kittenPrice.addTextChangedListener {
                        if (!buttonSave.isEnabled) {
                            buttonSave.isEnabled = true
                        }
                    }
                    kittenAge.isEnabled = isEditable
                    kittenAge.text = info.birthDate.toDate() // TODO: форматирование даты
                    kittenAge.addTextChangedListener {
                        if (!buttonSave.isEnabled) {
                            buttonSave.isEnabled = true
                        }
                    }
                    kittenAge.setOnClickListener {
                        val myFormat = "yyyy-MM-dd"

                        val sdf = SimpleDateFormat(myFormat)
                        val date = sdf.parse(info.birthDate)
                        val timeInMillis = date.time

                        val constraintBuilder = CalendarConstraints.Builder().setOpenAt(
                            timeInMillis //pass time in milli seconds
                        ).build()

                        val picker = MaterialDatePicker.Builder.datePicker()
                            .setTitleText("Select Date")
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
                    Glide.with(root).load(info.picture).into(kittenImage)
                }
            }
        }
        binding.buttonSave.setOnClickListener {
            with(binding) {
                viewModel.changeKittenInfo(
                    breed = kittenBread.text.toString(),
                    about = kittenAbout.text.toString(),
                    price = kittenPrice.text.toString().toInt(),
                    name = kittenName.text.toString(),
                    city = kittenName.text.toString(),
                    birthDate = kittenAge.text.toString(),
                )
                buttonSave.isEnabled = false
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