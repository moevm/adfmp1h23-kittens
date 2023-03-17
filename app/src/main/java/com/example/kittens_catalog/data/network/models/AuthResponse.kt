package com.example.kittens_catalog.data.network.models

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonReader

@JsonClass(generateAdapter = true)
data class AuthResponse (
    val success: Boolean,
)
class AuthResponseAdapter {
    @FromJson
    fun fromJson(reader: JsonReader, authResponseAdapter: JsonAdapter<AuthResponse>): AuthResponse {
        val readerCopy = reader.peekJson()
        var success: Boolean = false
        while(readerCopy.hasNext()) {
            when(readerCopy.nextName()) {
                "success" -> success = readerCopy.nextBoolean()
            }
        }
        return AuthResponse(success = success)
    }
}