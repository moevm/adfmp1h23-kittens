package com.example.kittens_catalog.features.kitten

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentKittenBinding
import com.example.kittens_catalog.databinding.FragmentMainBinding
import com.example.kittens_catalog.features.base.BaseFragment
import com.example.kittens_catalog.features.breeding.BreedingViewModel
import com.example.kittens_catalog.features.main.MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KittenFragment : BaseFragment<FragmentKittenBinding>() {

    private val viewModel: KittenViewModel by viewModels()
    private val args: KittenFragmentArgs by navArgs()

    override fun setupViewBinding(inflater: LayoutInflater): FragmentKittenBinding {
        return FragmentKittenBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseViewModel.doRefresh()
        initView()
    }

    fun initView() {
        val id = args.id
        viewModel.getKitten(id).observe(viewLifecycleOwner) {
            if (it != null) {
                with(binding) {
                    breedText.text = it.breed
                    nameText.text = it.name
                    aboutText.text = it.about
                    priceText.text = it.price.toString()
                    birthDateText.text = it.birthDate // TODO: форматирование даты
                    Glide.with(root).load(it.picture).into(imageView);
                }
            }
        }
    }
}