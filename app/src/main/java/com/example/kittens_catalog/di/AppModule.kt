package com.example.kittens_catalog.di

import com.example.kittens_catalog.data.network.models.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton




@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(Date()::class.java, Rfc3339DateJsonAdapter())
        .add(AuthRequestAdapter())
        .add(AuthResponseAdapter())
        .add(WhoAmIResponseAdapter())
        .add(RegisterRequestAdapter())
        .build()
}