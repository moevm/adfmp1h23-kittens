package com.example.kittens_catalog.data.network.models

import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class BirthDate(
    @Json(name = "gt") val gt: String,
    @Json(name = "lt") val lt: String
)