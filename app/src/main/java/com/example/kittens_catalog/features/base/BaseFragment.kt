package com.example.kittens_catalog.features.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.kittens_catalog.features.main.MainViewModel
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<T : ViewBinding>(layoutId: Int): Fragment(layoutId) {
    protected lateinit var navController: NavController
    protected val baseViewModel: MainViewModel by viewModels()
    protected abstract val binding: T
    protected fun navigate(current: Int, destination: NavDirections) {
        if (findNavController().currentDestination?.id == current) {
            findNavController().navigate(destination)
        }
    }

    fun showSnackbar(text:String, duration:Int = Snackbar.LENGTH_SHORT){
        Snackbar.make(binding.root, text, duration).show()
    }

}