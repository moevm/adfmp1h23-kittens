package com.example.kittens_catalog.data.network.models

import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class KittenItem(
    @Json(name = "about") val about: String,
    @Json(name = "birthDate") val birthDate: String,
    @Json(name = "created") val created: String,
    @Json(name = "updated") val updated: String,
    @Json(name = "breed") val breed: String,
    @Json(name = "city") val city: String,
    @Json(name = "hidden") val hidden: Boolean,
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "picture") val picture: String,
    @Json(name = "price") val price: Int,
    @Json(name = "userId") val userId: Int
)