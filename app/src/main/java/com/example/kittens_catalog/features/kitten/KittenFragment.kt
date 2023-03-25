package com.example.kittens_catalog.features.kitten

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentBreedingBinding
import com.example.kittens_catalog.databinding.FragmentKittenBinding
import com.example.kittens_catalog.databinding.FragmentMainBinding
import com.example.kittens_catalog.features.base.BaseFragment
import com.example.kittens_catalog.features.breeding.BreedingViewModel
import com.example.kittens_catalog.features.main.MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KittenFragment : BaseFragment<FragmentKittenBinding>(R.layout.fragment_kitten) {

    private val viewModel: KittenViewModel by viewModels()
    private val args: KittenFragmentArgs by navArgs()
    override val binding: FragmentKittenBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseViewModel.doRefresh()
        initView()
    }

    fun initView() {
        val id = args.id
        viewModel.getKitten(id).observe(viewLifecycleOwner) {kittenInfo->
            kittenInfo?.also{
                with(binding) {
                    kittenBread.setText(it.breed)
                    kittenBread.addTextChangedListener {
                    }
                    kittenName.setText(it.name)
                    kittenAbout.setText(it.about)
                    kittenPrice.setText(it.price.toString())
                    kittenAge.setText(it.birthDate) // TODO: форматирование даты
                    Glide.with(root).load(it.picture).into(kittenImage);
                }
            }
        }
    }
}