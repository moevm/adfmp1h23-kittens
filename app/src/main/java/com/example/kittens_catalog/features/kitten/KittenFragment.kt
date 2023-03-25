package com.example.kittens_catalog.features.kitten

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
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
        val id = args.id
        viewModel.getKitten(id).observe(viewLifecycleOwner) {kittenInfo->
            kittenInfo?.also{info ->
                with(binding) {
                    kittenBread.setText(info.breed)
                    kittenBread.addTextChangedListener {
                    }
                    kittenName.setText(info.name)
                    kittenAbout.setText(info.about)
                    kittenPrice.setText(info.price.toString())
                    kittenAge.text = info.birthDate.toDate() // TODO: форматирование даты
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
    }
}

