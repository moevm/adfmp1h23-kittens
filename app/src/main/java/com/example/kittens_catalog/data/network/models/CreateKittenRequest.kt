package com.example.kittens_catalog.data.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateKittenRequest(
    @Json(name = "about") val about: String?,
    @Json(name = "birthDate") val birthDate: String,
    @Json(name = "breed") val breed: String,
    @Json(name = "city") val city: String?,
    @Json(name = "name") val name: String,
    @Json(name = "price") val price: Int,
    @Json(name = "picture") val picture: String?
)