package com.example.kittens_catalog.data.network.interceptors

import android.content.Context
import android.content.SharedPreferences
import android.webkit.CookieManager
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CommonInterceptor @Inject constructor(private val preferences: SharedPreferences): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val cookie = preferences.getString("token", "")
        if(cookie != "" && cookie != null) {
            requestBuilder.addHeader("Cookie", cookie)
        }
        return chain.proceed(requestBuilder.build())
    }
}