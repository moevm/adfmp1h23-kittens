package com.example.kittens_catalog.data.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponse (
    val success: Boolean,
)