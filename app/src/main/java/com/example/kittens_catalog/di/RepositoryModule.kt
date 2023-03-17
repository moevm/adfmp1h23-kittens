package com.example.kittens_catalog.di

import com.example.kittens_catalog.data.repository.AuthRepository
import com.example.kittens_catalog.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideAuthRepository(repository: AuthRepositoryImpl): AuthRepository
}