package com.example.kittens_catalog.domain.entity


data class KittenInfo(
    val about: String,
    val birthDate: String,
    val breed: String,
    val city: String,
    val id: Int,
    val name: String,
    val price: Int,
    val picture: String,
    val userId: Int
)
