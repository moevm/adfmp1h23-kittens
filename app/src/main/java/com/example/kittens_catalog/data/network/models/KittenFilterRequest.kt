package com.example.kittens_catalog.data.network.models

import com.squareup.moshi.*


@JsonClass(generateAdapter = true)
data class KittenFilterRequest(
    @Json(name = "birthDate") val birthDate: BirthDate?,
    @Json(name = "breed") val breed: String?,
    @Json(name = "city") val city: String?
)