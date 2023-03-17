package com.example.kittens_catalog.di

import com.example.kittens_catalog.KittensApplication
import dagger.hilt.EntryPoints

object EntryPointStore {
    private lateinit var application: KittensApplication

    val appEntryPoint: AppEntryPoint

    get() = EntryPoints.get(application, AppEntryPoint::class.java)

    fun setApplication(application: KittensApplication) {
        this.application = application
    }
}