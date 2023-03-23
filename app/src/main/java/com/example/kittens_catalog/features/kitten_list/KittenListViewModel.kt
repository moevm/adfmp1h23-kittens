package com.example.kittens_catalog.features.kitten_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kittens_catalog.data.network.models.BirthDate
import com.example.kittens_catalog.data.network.models.KittenItem
import com.example.kittens_catalog.domain.interactors.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KittenListViewModel @Inject constructor(private val authInteractor: AuthInteractor): ViewModel() {
    private val _kittensData = MutableLiveData<List<KittenItem>?>(null)
    val kittenData: LiveData<List<KittenItem>?> get() = _kittensData
    private val _breeds = MutableLiveData<List<String>?>(null)
    val breeds: LiveData<List<String>?> get() = _breeds

    fun init() {
        _breeds.value = authInteractor.getBreeds()
    }

    fun search(
        city: String?,
        breed: String?,
        name: String?,
        birthDate: BirthDate?
    ): LiveData<List<KittenItem>?> {
        val kittens = authInteractor.getKittens(city, breed, name, birthDate)
//        val breeds = authInteractor.getBreeds()
        _kittensData.value = kittens
//        _breeds.value = breeds
        return _kittensData
    }
}