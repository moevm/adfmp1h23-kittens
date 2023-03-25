package com.example.kittens_catalog.data.network.models

import com.squareup.moshi.*
import java.io.IOException

@JsonClass(generateAdapter = true)
data class AuthResponse (
    @Json(name = "success") val success: Boolean,
)