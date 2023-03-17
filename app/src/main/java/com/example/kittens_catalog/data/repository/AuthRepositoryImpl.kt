package com.example.kittens_catalog.data.repository
import com.example.kittens_catalog.data.network.api.AuthApi
import com.example.kittens_catalog.data.network.models.AuthRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
) : AuthRepository {
    override fun auth(login: String, password: String): Boolean {
        val params = AuthRequest(login, password)
        return authApi.auth(params).execute().body()?.success ?: false
    }

    override fun whoAmI() {
        TODO("Not yet implemented")
    }
}