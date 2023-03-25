package com.example.kittens_catalog.data.network.models

import com.squareup.moshi.*
import okhttp3.internal.format
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


@JsonClass(generateAdapter = true)
data class WhoAmIResponse(
    @Json(name = "about") val about: String?,
    @Json(name = "birthDate") val birthDate: Date?,
    @Json(name = "created") val created: Date,
    @Json(name = "updated") val updated: Date,
    @Json(name = "firstName") val firstName: String,
    @Json(name = "id") val id: Int,
    @Json(name = "lastName") val lastName: String,
    @Json(name = "login") val login: String,
    @Json(name = "middleName") val middleName: String?,
    @Json(name = "picture") val picture: String?
)
