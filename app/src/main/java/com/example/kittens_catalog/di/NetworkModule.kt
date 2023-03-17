package com.example.kittens_catalog.di

import com.example.kittens_catalog.data.network.api.AuthApi
import com.x5.courierapp.data.network.RetrofitFactory
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
        .baseUrl("http://localhost:3001")
        .build()
        .create(AuthApi::class.java)
}