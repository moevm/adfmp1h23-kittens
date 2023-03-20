package com.example.kittens_catalog.domain.interactors

import com.example.kittens_catalog.data.network.models.WhoAmIResponse
import com.example.kittens_catalog.data.repository.AuthRepository
import javax.inject.Inject

class AuthInteractor @Inject constructor(private val authRepository: AuthRepository) {
    fun auth(login: String, password: String): Boolean {
        return authRepository.auth(login, password)
    }

    fun whoAmI(): WhoAmIResponse? {
        return authRepository.whoAmI()
    }
}