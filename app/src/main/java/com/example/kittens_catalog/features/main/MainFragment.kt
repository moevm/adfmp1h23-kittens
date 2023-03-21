package com.example.kittens_catalog.features.main

import android.os.Bundle
import android.text.Layout.Directions
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentMainBinding
import com.example.kittens_catalog.features.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainFragment: BaseFragment<FragmentMainBinding>() {
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()

    }

    private fun initView() {
    }

    private fun subscribeUi() {
        viewModel.whoAmI().observe(viewLifecycleOwner) {
            if (!it) {
                navigate(R.id.mainFragment, MainFragmentDirections.actionMainFragmentToAuthFragment())
            } else {
                navigate(R.id.mainFragment, MainFragmentDirections.actionMainFragmentToBreedingFragment())
            }
        }
        Toast.makeText(requireContext(), "auth: ${viewModel.isAuthenticated.value} data: ${viewModel.personalData.value?.lastName}", Toast.LENGTH_LONG).show()
    }

    override fun setupViewBinding(inflater: LayoutInflater): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater)
    }
}