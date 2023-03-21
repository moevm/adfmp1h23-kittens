package com.example.kittens_catalog.data.network.models

import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class RegisterRequest (
    val firstName: String,
    val lastName: String,
    val login: String,
    val password: String?
)

class RegisterRequestAdapter {
    @ToJson
    fun toJson(
        writer: JsonWriter, value: RegisterRequest?
    ) {
        if (value == null) {
            writer.nullValue()
        } else {
            writer.beginObject()
            writer.name("login")
            writer.value(value.login)
            writer.name("password")
            writer.value(value.password)
            writer.name("firstName")
            writer.value(value.firstName)
            writer.name("lastName")
            writer.value(value.lastName)
            writer.endObject()
        }
    }
}