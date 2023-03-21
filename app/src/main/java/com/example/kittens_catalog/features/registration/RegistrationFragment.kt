package com.example.kittens_catalog.features.registration

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentBreedingBinding
import com.example.kittens_catalog.databinding.FragmentRegistrationBinding
import com.example.kittens_catalog.features.base.BaseFragment
import com.example.kittens_catalog.features.breeding.BreedingViewModel
import com.example.kittens_catalog.features.main.MainFragmentDirections
import com.example.kittens_catalog.features.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {
    private val viewModel: RegistrationViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registrationButton.setOnClickListener {
            val login = binding.loginField.text.toString()
            val lastName = binding.lastNameField.text.toString()
            val firstName = binding.firstNameField.text.toString()
            val password = binding.passwordField.text.toString()
            val res = viewModel.register(login, lastName, firstName, password)
            if (res) {
                Toast.makeText(requireContext(), "You are successfully registered", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun setupViewBinding(inflater: LayoutInflater): FragmentRegistrationBinding {
        return FragmentRegistrationBinding.inflate(inflater)
    }

}