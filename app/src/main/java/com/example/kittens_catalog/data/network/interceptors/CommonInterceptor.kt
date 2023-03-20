package com.example.kittens_catalog.data.network.interceptors

import android.webkit.CookieManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CommonInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val cookieManager = CookieManager.getInstance()
        val cookie = cookieManager.getCookie("http://10.0.2.2:3001")
        if (cookie != null) {
            requestBuilder.addHeader("cookie", cookie)
        }
        return chain.proceed(requestBuilder.build())
    }
}