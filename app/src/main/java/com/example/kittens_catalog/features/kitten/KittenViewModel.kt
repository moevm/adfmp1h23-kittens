package com.example.kittens_catalog.features.kitten

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kittens_catalog.domain.entity.KittenInfo
import com.example.kittens_catalog.domain.interactors.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KittenViewModel @Inject constructor(private val authInteractor: AuthInteractor) : ViewModel() {
    private val _kitten = MutableLiveData<KittenInfo?>(null)
    val kitten: LiveData<KittenInfo?> get() = _kitten

    fun getKitten(id: Int): LiveData<KittenInfo?> {
        val kitten = authInteractor.getOne(id)
        _kitten.value = kitten
        return _kitten
    }

    fun changeKittenInfo(){

    }
}

