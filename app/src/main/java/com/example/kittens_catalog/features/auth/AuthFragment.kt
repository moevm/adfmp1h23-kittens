package com.example.kittens_catalog.features.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentAuthBinding
import com.example.kittens_catalog.features.base.BaseFragment
import com.example.kittens_catalog.features.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment: BaseFragment<FragmentAuthBinding>(R.layout.fragment_auth) {

    private val viewModel: AuthViewModel by viewModels()
    override val binding: FragmentAuthBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            button.setOnClickListener {
                val auth = viewModel.auth(binding.loginField.text.toString(), binding.passwordField.text.toString())
                baseViewModel.doRefresh()
                if (auth) {
                    navController.navigate(R.id.mainFragment)
                }
            }
            signUp.setOnClickListener {
                navController.navigate(R.id.registrationFragment)
            }
            loginField.setText("boris")
            passwordField.setText("borisBORIS123")
        }
    }


}