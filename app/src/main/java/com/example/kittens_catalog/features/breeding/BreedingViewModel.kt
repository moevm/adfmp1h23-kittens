package com.example.kittens_catalog.features.breeding

import android.webkit.CookieManager
import androidx.lifecycle.ViewModel

class BreedingViewModel : ViewModel() {
    fun logOut() {
        val cookieManager = CookieManager.getInstance()
        val cookie = cookieManager.getCookie("10.0.0.2:3001")
        if (cookie != null) {
            cookieManager.setCookie("10.0.0.2:3001", null)
        }
    }
}