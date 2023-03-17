package com.example.kittens_catalog.data.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WhoAmIResponse(
    val about: String?,
    val authId: Int,
    val birthDate: String,
    val created: String,
    val firstName: String,
    val id: Int,
    val languageId: String?,
    val lastName: String,
    val login: String,
    val middleName: String?,
    val picture: String?,
    val staffId: String?,
    val suspended: Boolean,
    val updated: String
)