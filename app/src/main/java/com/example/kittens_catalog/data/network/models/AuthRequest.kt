package com.example.kittens_catalog.data.network.models

import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class AuthRequest (
    val login: String,
    val password: String
)

class AuthRequestAdapter {
    @ToJson
    fun toJson(
        writer: JsonWriter, value: AuthRequest?
    ) {
        if (value == null) {
            writer.nullValue()
        } else {
            writer.beginObject()
            writer.name("login")
            writer.value(value.login)
            writer.name("password")
            writer.value(value.password)
            writer.endObject()
        }
    }
}