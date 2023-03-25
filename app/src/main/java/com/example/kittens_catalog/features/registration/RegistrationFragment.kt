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
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.FragmentBreedingBinding
import com.example.kittens_catalog.databinding.FragmentMainBinding
import com.example.kittens_catalog.databinding.FragmentRegistrationBinding
import com.example.kittens_catalog.features.base.BaseFragment
import com.example.kittens_catalog.features.breeding.BreedingViewModel
import com.example.kittens_catalog.features.main.MainFragmentDirections
import com.example.kittens_catalog.features.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(R.layout.fragment_registration) {
    private val viewModel: RegistrationViewModel by viewModels()
    override val binding: FragmentRegistrationBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registrationButton.setOnClickListener {
            with(binding) {
                if (
                    loginField.text.isNullOrEmpty() || lastNameField.text.isNullOrEmpty() || firstNameField.text.isNullOrEmpty() || passwordField.text.isNullOrEmpty()
                ) {
                    val login = loginField.text.toString()
                    val lastName = lastNameField.text.toString()
                    val firstName = firstNameField.text.toString()
                    val password = passwordField.text.toString()
                    val res = viewModel.register(login, lastName, firstName, password)
                    if (res) {
                        Toast.makeText(
                            requireContext(),
                            "You are successfully registered",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Some error occurred while registration",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                } else {
                    Toast.makeText(
                        requireContext(),
                        "You need to fill all required fields!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}