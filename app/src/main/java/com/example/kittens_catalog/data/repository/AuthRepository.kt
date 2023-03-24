package com.example.kittens_catalog.data.repository

import com.example.kittens_catalog.data.network.models.KittenFilterRequest
import com.example.kittens_catalog.data.network.models.KittenItem
import com.example.kittens_catalog.data.network.models.WhoAmIResponse

interface AuthRepository {
    fun auth(login: String, password: String): Boolean
    fun whoAmI(): WhoAmIResponse?
    fun register(login: String, lastName: String, firstName: String, password: String): Boolean
    fun getKitten(filter: KittenFilterRequest): List<KittenItem>?
    fun getMineKittens(filter: KittenFilterRequest): List<KittenItem>?
    fun getBreed(): List<String>?
    fun getOne(id: Int): KittenItem?
}