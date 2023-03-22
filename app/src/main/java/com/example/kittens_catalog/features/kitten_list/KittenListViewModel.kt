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

    fun getKittens(): LiveData<List<KittenItem>?> {
        val kittens = authInteractor.getKittens("string", "string", BirthDate("2001-03-16T18:12:17.397Z", "2026-03-16T18:12:17.397Z"))
        _kittensData.value = kittens
        return _kittensData
    }
}