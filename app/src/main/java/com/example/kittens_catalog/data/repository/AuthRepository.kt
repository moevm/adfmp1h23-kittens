package com.example.kittens_catalog.data.repository

import com.example.kittens_catalog.data.network.models.WhoAmIResponse

interface AuthRepository {
    fun auth(login: String, password: String): Boolean
    fun whoAmI(): WhoAmIResponse?
    fun register(login: String, lastName: String, firstName: String, password: String): Boolean
}