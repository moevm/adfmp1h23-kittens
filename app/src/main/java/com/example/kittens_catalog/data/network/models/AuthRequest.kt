package com.example.kittens_catalog.data.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthRequest (
    val login: String,
    val password: String
)