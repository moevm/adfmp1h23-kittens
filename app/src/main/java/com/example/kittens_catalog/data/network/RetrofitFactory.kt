package com.x5.courierapp.data.network

import com.example.kittens_catalog.data.network.interceptors.AuthInterceptor
import com.example.kittens_catalog.data.network.result.ResultAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitFactory @Inject constructor(
    private val callFactory: ResultAdapterFactory,
    private val authInterceptor: AuthInterceptor,
    private val moshi: Moshi,
) {
    private var authRetrofitBuilder: Retrofit.Builder? = null
    fun createAuthRetrofitBuilder() = authRetrofitBuilder
        ?: createRetrofitBuilder()
            .also { authRetrofitBuilder = it }

    fun createRetrofitBuilder(
    ): Retrofit.Builder = Retrofit.Builder()
        .client(createOkHttpClient())
        .addCallAdapterFactory(callFactory)
        .addConverterFactory(MoshiConverterFactory.create(moshi))

    private fun createOkHttpClient(
    ): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(60, TimeUnit.SECONDS)
        readTimeout(60, TimeUnit.SECONDS)
        writeTimeout(60, TimeUnit.SECONDS)
        cache(null)
        addInterceptor(authInterceptor)
//        if (withAuth) addInterceptor(tokenInterceptor) if interceptors come out to play
    }.build()
}