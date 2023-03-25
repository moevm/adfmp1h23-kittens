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

    fun changeKittenInfo(
        info: KittenInfo
    ){
        if(_kitten.value?.about != info.about ||
            _kitten.value?.birthDate != info.birthDate ||
            _kitten.value?.breed != info.breed ||
            _kitten.value?.city != info.city ||
            _kitten.value?.name != info.name ||
            _kitten.value?.price != info.price
        ){

        }
    }
}

data class StateKittenWindow(
    var kittenInfo: KittenInfo?,
    var kittenInfoNew: KittenInfo?,
    var changeData: Boolean = false,
)
