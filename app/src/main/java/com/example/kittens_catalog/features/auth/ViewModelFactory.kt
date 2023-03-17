package com.example.kittens_catalog.features.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject

class AuthViewModelFactory @Inject constructor(
    private val authViewModel: AuthViewModel): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AuthViewModel::class.java)){
            return authViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}