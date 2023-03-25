package com.example.kittens_catalog.features.kitten_create

import androidx.lifecycle.ViewModel
import com.example.kittens_catalog.data.network.models.CreateKittenRequest
import com.example.kittens_catalog.domain.interactors.AuthInteractor
import com.squareup.moshi.Json
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KittenCreateViewModel  @Inject constructor(private val authInteractor: AuthInteractor): ViewModel() {
    fun createKitten(
        about: String,
        birthDate: String,
        breed: String,
        city: String,
        name: String,
        price: Int,
        picture: String?,
    ) {
        authInteractor.createKitten(CreateKittenRequest(
            about = about,
            birthDate = birthDate,
            breed = breed,
            city = city,
            name = name,
            price = price,
            picture = picture
        ))
    }
}