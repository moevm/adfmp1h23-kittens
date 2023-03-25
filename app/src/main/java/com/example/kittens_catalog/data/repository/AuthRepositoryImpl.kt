package com.example.kittens_catalog.data.repository

import android.content.SharedPreferences
import com.example.kittens_catalog.data.mappers.mapper
import com.example.kittens_catalog.data.network.api.AuthApi
import com.example.kittens_catalog.data.network.models.*
import com.example.kittens_catalog.domain.entity.KittenInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val preferences: SharedPreferences
) : AuthRepository {
    override fun auth(login: String, password: String): Boolean {
        val params = AuthRequest(login, password)
        val res = authApi.auth(params).execute()
        val cookie = res.headers()["Set-Cookie"]
        if (cookie != null)
            preferences.edit().putString("token", cookie).apply()
        return res.body()?.success ?: false
    }

    override fun whoAmI(): WhoAmIResponse? {
        return authApi.whoAmI().execute().body()
    }

    override fun register(
        login: String,
        lastName: String,
        firstName: String,
        password: String
    ): Boolean {
        val params = RegisterRequest(
            login = login,
            lastName = lastName,
            firstName = firstName,
            password = password
        )
        val res = authApi.register(params).execute()
        return res.body()?.success ?: false
    }

    override fun getKitten(filter: KittenFilterRequest): List<KittenItem>? {
        return authApi.getKittens(filter).execute().body()
    }

    override fun getMineKittens(filter: KittenFilterRequest): List<KittenItem>? {
        return authApi.getMineKittens(filter).execute().body()
    }

    override fun getBreed(): List<String>? {
        return authApi.getBreeds().execute().body()
    }

    override fun getOne(id: Int): KittenInfo? {
        return authApi.getOne(id).execute().body().mapper()
    }

    override fun createKitten(kittenData: CreateKittenRequest): KittenInfo? {
        return authApi.createKitten(kittenData).execute().body().mapper()
    }

    override fun updateKitten(kittenData: UpdateKittenRequest): KittenInfo? {
        return authApi.updateKitten(kittenData).execute().body().mapper()
    }
}