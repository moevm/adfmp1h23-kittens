package com.example.kittens_catalog.data.network.api
import com.example.kittens_catalog.data.network.models.AuthRequest
import com.example.kittens_catalog.data.network.models.AuthResponse
import com.example.kittens_catalog.data.network.models.WhoAmIResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth")
    fun auth(@Body authParams: AuthRequest): Call<AuthResponse>

    @GET("/who-am-i")
    fun whoAmI(): Call<WhoAmIResponse?>
}