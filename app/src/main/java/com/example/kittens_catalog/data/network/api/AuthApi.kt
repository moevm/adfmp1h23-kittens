package com.example.kittens_catalog.data.network.api
import com.example.kittens_catalog.data.network.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth")
    fun auth(@Body authParams: AuthRequest): Call<AuthResponse>

    @GET("/who-am-i")
    fun whoAmI(): Call<WhoAmIResponse?>

    @POST("/register")
    fun register(@Body registerParams: RegisterRequest): Call<AuthResponse>

    @POST("/kitten/get")
    fun getKittens(@Body kittenFilterParams: KittenFilterRequest): Call<List<KittenItem>?>

    @POST("/kitten/get-mine")
    fun getMineKittens(@Body kittenFilterParams: KittenFilterRequest): Call<List<KittenItem>?>

    @GET("/kitten/get-breeds")
    fun getBreeds(): Call<List<String>>
}