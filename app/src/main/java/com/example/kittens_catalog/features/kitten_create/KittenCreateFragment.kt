package com.example.kittens_catalog.features.kitten_create

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentKittenBinding
import com.example.kittens_catalog.features.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KittenCreateFragment : BaseFragment<FragmentKittenBinding>() {

    private val viewModel: KittenCreateViewModel by viewModels()

    override fun setupViewBinding(inflater: LayoutInflater): FragmentKittenBinding {
        return FragmentKittenBinding.inflate(inflater)
    }

}