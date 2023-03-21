package com.example.kittens_catalog.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kittens_catalog.data.network.models.WhoAmIResponse
import com.example.kittens_catalog.domain.interactors.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): ViewModel() {

    private val _isAuthenticated = MutableLiveData<Boolean>(false)
    val isAuthenticated: LiveData<Boolean> get() = _isAuthenticated
    private val _personalData = MutableLiveData<WhoAmIResponse?>(null)
    val personalData: LiveData<WhoAmIResponse?> get() = _personalData

    fun onStart() {
        doRefresh()
    }

    fun doRefresh() {
        whoAmI()
    }

    fun whoAmI() {
        viewModelScope.launch {
            val me = authInteractor.whoAmI()
            _isAuthenticated.value = me != null
            if (me != null) _personalData.value = me
        }
    }
}