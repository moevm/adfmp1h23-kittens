package com.example.kittens_catalog.features.breeding

import android.content.Context
import android.content.SharedPreferences
import android.webkit.CookieManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

class BreedingViewModel @Inject constructor(private val preferences: SharedPreferences): ViewModel() {
    fun logOut() {
        viewModelScope.launch {
            preferences.edit().putString("token", "").apply()
        }
    }
}