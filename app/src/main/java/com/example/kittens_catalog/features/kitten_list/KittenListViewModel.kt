package com.example.kittens_catalog.features.kitten_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kittens_catalog.data.network.models.BirthDate
import com.example.kittens_catalog.data.network.models.KittenItem
import com.example.kittens_catalog.domain.interactors.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import hilt_aggregated_deps._dagger_hilt_android_internal_managers_ServiceComponentManager_ServiceComponentBuilderEntryPoint
import javax.inject.Inject

@HiltViewModel
class KittenListViewModel @Inject constructor(private val authInteractor: AuthInteractor): ViewModel() {
    private val _kittensData = MutableLiveData<List<KittenItem>?>(null)
    val kittenData: LiveData<List<KittenItem>?> get() = _kittensData

    private val _breeds = MutableLiveData<List<String>?>(null)
    val breeds: LiveData<List<String>?> get() = _breeds

    private val _states = MutableLiveData<KittenStates?>(null)
    val states: LiveData<KittenStates?> get() = _states

    private val _screenType = MutableLiveData<Boolean>(false) // true -- mine; false -- all
    val screenType: LiveData<Boolean> get() = _screenType


    fun init() {
        _breeds.value = authInteractor.getBreeds()
    }

    fun setScreenType(type: Boolean) {
        _screenType.value = type
    }

    fun setStates(name: String?, city: String?, breed: String?, birthDate: BirthDate?): LiveData<KittenStates?> {
        _states.value = KittenStates(
            name = name ?: states.value?.name,
            city = city ?: states.value?.city,
            breed = breed ?: states.value?.breed,
            birthDate = birthDate ?: states.value?.birthDate)
        return _states
    }

    private fun searchAll(
        city: String?,
        breed: String?,
        name: String?,
        birthDate: BirthDate?
    ): LiveData<List<KittenItem>?> {
        val kittens = authInteractor.getKittens(city, breed, name, birthDate)
        _kittensData.value = kittens
        return _kittensData
    }

    private fun searchMine(
        city: String?,
        breed: String?,
        name: String?,
        birthDate: BirthDate?,
    ): LiveData<List<KittenItem>?> {
        val kittens = authInteractor.getMineKittens(city, breed, name, birthDate)
        _kittensData.value = kittens
        return _kittensData
    }

    fun search(
        city: String?,
        breed: String?,
        name: String?,
        birthDate: BirthDate?
    ): LiveData<List<KittenItem>?> {
        return if (_screenType.value?.equals(true)!!) {
            searchMine(city, breed, name, birthDate)
        } else
            searchAll(city, breed, name, birthDate)
    }
}