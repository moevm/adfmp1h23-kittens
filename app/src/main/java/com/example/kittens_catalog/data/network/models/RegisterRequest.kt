package com.example.kittens_catalog.data.network.models

import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class RegisterRequest (
    @Json(name = "firstName") val firstName: String,
    @Json(name = "lastName") val lastName: String,
    @Json(name = "login") val login: String,
    @Json(name = "password") val password: String?
)