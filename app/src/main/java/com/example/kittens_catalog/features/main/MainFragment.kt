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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val  binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        initView()
        subscribeUi()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
        viewModel.isAuthenticated.observe(this, Observer {
            viewModel.doRefresh()
        })
    }

    private fun initView() {
    }

    private fun subscribeUi() {
        viewModel.whoAmI()
        Toast.makeText(requireContext(), "auth: ${viewModel.isAuthenticated.value} data: ${viewModel.personalData.value?.lastName}", Toast.LENGTH_LONG).show()
        if (viewModel.isAuthenticated.value == false) {
            navigateToAuthFragment()
        } else {
            navigateToBreedingFragment()
        }
    }

    private fun navigateToAuthFragment() {
        if (navController.currentDestination?.id == R.id.mainFragment) {
            val direction = MainFragmentDirections.actionMainFragmentToAuthFragment()
            navController.navigate(direction)
        }
    }

    private fun navigateToBreedingFragment() {
        if (navController.currentDestination?.id == R.id.mainFragment) {
            val direction = MainFragmentDirections.actionMainFragmentToBreedingFragment()
            navController.navigate(direction)
        }
    }
}