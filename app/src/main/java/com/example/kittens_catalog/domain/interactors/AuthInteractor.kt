package com.example.kittens_catalog.domain.interactors

import com.example.kittens_catalog.data.network.models.*
import com.example.kittens_catalog.data.repository.AuthRepository
import javax.inject.Inject

class AuthInteractor @Inject constructor(private val authRepository: AuthRepository) {
    fun auth(login: String, password: String): Boolean {
        return authRepository.auth(login, password)
    }

    fun whoAmI(): WhoAmIResponse? {
        return authRepository.whoAmI()
    }

    fun register(login: String, lastName: String, firstName: String, password: String): Boolean {
        return authRepository.register(login, lastName, firstName, password)
    }

    fun getKittens(city: String?, breed: String?, birthDate: BirthDate?): List<KittenItem>? {
        return authRepository.getKitten(KittenFilterRequest(birthDate, breed, city))
    }
}