package com.example.kittens_catalog.features.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
                    findNavController().navigate(R.id.mainFragment)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Not authorized!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            signUp.setOnClickListener {
                findNavController().navigate(R.id.registrationFragment)
            }

            about.setOnClickListener {
                findNavController().navigate(R.id.aboutItemFragment)
            }
            loginField.setText("boris")
            passwordField.setText("borisBORIS123")
        }
    }


}