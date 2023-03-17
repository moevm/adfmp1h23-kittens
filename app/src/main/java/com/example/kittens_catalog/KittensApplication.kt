package com.example.kittens_catalog

import android.app.Application
import com.example.kittens_catalog.di.EntryPointStore
import dagger.hilt.EntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KittensApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        EntryPointStore.setApplication(this)
    }
}