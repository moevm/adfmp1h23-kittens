package com.example.kittens_catalog.data.network.models

import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class AuthRequest (
    @Json(name = "login") val login: String,
    @Json(name = "password") val password: String
)
