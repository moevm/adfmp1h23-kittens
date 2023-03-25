package com.example.kittens_catalog.features.registration

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kittens_catalog.domain.interactors.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val authInteractor: AuthInteractor) : ViewModel() {
    fun register(login: String, lastName: String, firstName: String, password: String): Boolean {
        var register = false
        viewModelScope.launch {
            register = authInteractor.register(login, lastName, firstName, password)
        }
        return register
    }
}