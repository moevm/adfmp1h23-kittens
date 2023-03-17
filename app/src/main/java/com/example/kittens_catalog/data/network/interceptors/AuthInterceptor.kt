package com.example.kittens_catalog.data.network.interceptors

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

@Singleton
class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val response = chain.proceed(requestBuilder.build())
        println(response.header("Cookie"))
        return response
    }
}