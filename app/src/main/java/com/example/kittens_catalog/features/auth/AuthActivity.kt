package com.example.kittens_catalog.features.auth


import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.kittens_catalog.R
import com.example.kittens_catalog.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity: AppCompatActivity() {
    private val viewModel: AuthViewModel by viewModels();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = LoginFragmentBinding.inflate(layoutInflater)
        setContentView(R.layout.login_fragment)

        binding.button.setOnClickListener {
            Log.d("in", "on click")
            viewModel.auth(binding.loginField.text.toString(), binding.passwordField.toString())
        }
    }

}