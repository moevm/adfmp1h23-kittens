package com.example.kittens_catalog.features.auth
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kittens_catalog.databinding.LoginFragmentBinding


class AuthFragment: Fragment() {
    private var _binding: LoginFragmentBinding? = null
    private val  binding get() = _binding!!

    private val viewModel: AuthViewModel by activityViewModels()

    companion object {
        fun newInstance() = AuthFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            viewModel.auth(binding.loginField.text.toString(), binding.passwordField.toString())
        }
    }
}