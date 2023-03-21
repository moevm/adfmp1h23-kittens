package com.example.kittens_catalog.features.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.kittens_catalog.databinding.FragmentRegistrationBinding
import com.example.kittens_catalog.features.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseFragment<T : ViewBinding>: Fragment() {
    protected lateinit var navController: NavController
    protected val baseViewModel: MainViewModel by viewModels()
    private var _binding: T? = null
    protected val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = setupViewBinding(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
    }

    protected fun navigate(current: Int, destination: NavDirections) {
        if (navController.currentDestination?.id == current) {
            navController.navigate(destination)
        }
    }
    abstract fun setupViewBinding(inflater: LayoutInflater) : T
}