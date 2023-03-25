package com.example.kittens_catalog.extensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.toDate(
    pattern: String = "dd.MM.yyyy"
): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    return formatter.format(formatter.parse(this)?:"")?:this
}
