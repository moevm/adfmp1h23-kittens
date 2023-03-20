package com.example.kittens_catalog.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import android.webkit.CookieManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val response = chain.proceed(requestBuilder.build())
        val cookie = response.header("Set-Cookie")

        val cookieManager = CookieManager.getInstance()
        val url = response.request.url.host;

        if (response.header("Set-Cookie") != null) {
            if(cookieManager.getCookie(url) == null)
            cookieManager.setCookie(url, cookie)
        }
        println(response.header("Set-Cookie"))
        return response
    }
}