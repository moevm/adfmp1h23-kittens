package com.example.kittens_catalog.di

import com.example.kittens_catalog.data.network.RetrofitFactory
import com.example.kittens_catalog.data.network.api.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun authApi(retrofitFactory: RetrofitFactory): AuthApi = retrofitFactory
        .createRetrofitBuilder()
        .baseUrl("http://46.101.125.110:3001")
        .build()
        .create(AuthApi::class.java)
}