package com.example.kittens_catalog.features.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kittens_catalog.R
import com.example.kittens_catalog.domain.interactors.AuthInteractor
import kotlinx.coroutines.launch

import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): ViewModel() {
    private var viewState: AuthState = AuthState()
    fun auth(login: String, password: String) {
        viewState = viewState.copy(isAuthLoading = true)
        viewModelScope.launch {
            authInteractor.auth(login, password)
        }
    }
}