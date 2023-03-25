package com.example.kittens_catalog.data.mappers

import com.example.kittens_catalog.data.network.models.KittenItem
import com.example.kittens_catalog.domain.entity.KittenInfo

fun KittenItem?.mapper(): KittenInfo?{
    return this?.let {
        KittenInfo(
            about,
            birthDate,
            breed,
            city,
            id,
            name,
            price,
            picture?:"",
            userId
        )
    }
}