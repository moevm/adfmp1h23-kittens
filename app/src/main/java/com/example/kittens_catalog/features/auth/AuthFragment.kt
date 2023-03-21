package com.example.kittens_catalog.features.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentAuthBinding
import com.example.kittens_catalog.features.base.BaseFragment
import com.example.kittens_catalog.features.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment: BaseFragment() {

    private var _binding: FragmentAuthBinding? = null
    private val  binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            val auth = viewModel.auth(binding.loginField.text.toString(), binding.passwordField.text.toString())
            baseViewModel.doRefresh()
            if (auth) {
                navController.navigate(R.id.mainFragment)
//                navigate(R.id.auth_fragment, AuthFragmentDirections.actionAuthFragmentToBreedingFragment())
            }
        }
    }

}