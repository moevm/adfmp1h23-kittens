package com.example.kittens_catalog.data.network.models

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonReader
import java.io.IOException

@JsonClass(generateAdapter = true)
data class AuthResponse (
    val success: Boolean,
)

class AuthResponseAdapter {
    @FromJson
    fun fromJson(reader: JsonReader, authResponseAdapter: JsonAdapter<AuthResponse>): AuthResponse? {
        try {
            reader.peekJson().beginObject()
        } catch (err: IOException) {
            return null
        }
        return authResponseAdapter.fromJson(reader)
    }
}