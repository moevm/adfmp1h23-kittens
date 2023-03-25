package com.example.kittens_catalog.data.repository

import com.example.kittens_catalog.data.network.models.*
import com.example.kittens_catalog.domain.entity.KittenInfo

interface AuthRepository {
    fun auth(login: String, password: String): Boolean
    fun whoAmI(): WhoAmIResponse?
    fun register(login: String, lastName: String, firstName: String, password: String): Boolean
    fun getKitten(filter: KittenFilterRequest): List<KittenItem>?
    fun getMineKittens(filter: KittenFilterRequest): List<KittenItem>?
    fun getBreed(): List<String>?
    fun getOne(id: Int): KittenInfo?
    fun createKitten(kittenData: CreateKittenRequest): KittenInfo?
    fun updateKitten(kittenData: UpdateKittenRequest): KittenInfo?
}