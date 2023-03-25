package com.example.kittens_catalog.features.kitten_create

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentKittenCreateBinding
import com.example.kittens_catalog.features.base.BaseFragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
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
        with(binding){
            kittenAge.setOnClickListener {
                val myFormat = "yyyy-MM-dd"
                val sdf = SimpleDateFormat(myFormat)
                val date = sdf.parse("2023-05-10")
                val timeInMillis = date.time
                val constraintBuilder = CalendarConstraints.Builder().setOpenAt(
                    timeInMillis //pass time in milli seconds
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
                viewModel.createKitten(
                    about = kittenAbout.text.toString(),
                    birthDate = kittenAge.text.toString(),
                    breed = kittenBread.text.toString(),
                    city = kittenCity.text.toString(),
                    name = kittenName.text.toString(),
                    price = kittenPrice.text.toString().toInt(),
                    picture = null
                )
            }
        }
    }

}