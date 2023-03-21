package com.example.kittens_catalog.data.repository
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.kittens_catalog.data.network.api.AuthApi
import com.example.kittens_catalog.data.network.models.AuthRequest
import com.example.kittens_catalog.data.network.models.WhoAmIResponse
import com.example.kittens_catalog.di.SharedPreferencesModule
import dagger.hilt.android.qualifiers.ApplicationContext
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
}