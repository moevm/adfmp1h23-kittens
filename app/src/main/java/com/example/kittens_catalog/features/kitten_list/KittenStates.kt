package com.example.kittens_catalog.features.kitten_list

import com.example.kittens_catalog.data.network.models.BirthDate

data class KittenStates (
    val name: String?,
    val breed: String?,
    val city: String?,
    val birthDate: BirthDate?
        )