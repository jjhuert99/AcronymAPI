package com.example.acronymapi.di

import com.example.acronymapi.common.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideCoroutines() = Dispatchers

    @Singleton
    @Provides
    fun provideRetrofitFactory() = RetrofitFactory

    @Singleton
    @Provides
    fun provideIO() = Dispatchers.IO

    @Singleton
    @Provides
    fun provideUI() = Dispatchers.Main
}
