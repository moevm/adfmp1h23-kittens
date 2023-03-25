package com.example.kittens_catalog.features.kitten_list

import com.example.kittens_catalog.data.network.models.BirthDate

data class KittenStates (
    var name: String?,
    var breed: String?,
    var city: String?,
    var birthDate: BirthDate?
        )