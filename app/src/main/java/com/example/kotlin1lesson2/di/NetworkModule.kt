package com.example.kotlin1lesson2.di

import com.example.kotlin1lesson2.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    val retrofitClient = RetrofitClient()

    @Singleton
    @Provides
    fun providerCharacterApiService() = retrofitClient.providerCharacterApiService()

    @Singleton
    @Provides
    fun provideEpisodeApiService() = retrofitClient.providerEpisodesApiService()

    @Singleton
    @Provides
    fun provideLocationsApiService() = retrofitClient.providerLocationsApiService()
}