package com.example.kittens_catalog.features.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kittens_catalog.domain.interactors.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): ViewModel() {
    private var viewState: AuthState = AuthState()
    fun auth(login: String, password: String): Boolean {
        viewState = viewState.copy(isAuthLoading = true)
        var auth = false
        viewModelScope.launch {
            auth = authInteractor.auth(login, password)
       }
        return auth
    }
}