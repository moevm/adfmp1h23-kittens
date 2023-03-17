package com.example.kittens_catalog.data.repository

interface AuthRepository {
    fun auth(login: String, password: String): Boolean
    fun whoAmI()
}