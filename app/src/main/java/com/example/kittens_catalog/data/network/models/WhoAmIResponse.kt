package com.example.kittens_catalog.data.network.models

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonReader
import okhttp3.internal.format
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@JsonClass(generateAdapter = true)
data class WhoAmIResponse(
    val about: String?,
    val birthDate: Date?,
    val created: Date,
    val updated: Date,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val login: String,
    val middleName: String?,
    val picture: String?
)

class WhoAmIResponseAdapter {
    @FromJson
    fun fromJson(reader: JsonReader, whoAmIResponseAdapter: JsonAdapter<WhoAmIResponse>): WhoAmIResponse? {
        try {
            reader.peekJson().beginObject()
        } catch (err: IOException) {
            return null
        }
        return whoAmIResponseAdapter.fromJson(reader)
    }
}