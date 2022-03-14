package com.example.acronymapi.di

import com.example.acronymapi.common.RetrofitFactory
import com.example.acronymapi.network.AcronymEndpoints
import com.example.acronymapi.network.AcronymRepo
import com.example.acronymapi.network.AcronymRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AcronymModule {
    private const val BASE_URL = "http://www.nactem.ac.uk/software/acromine/"

    @Singleton
    @Provides
    fun provideAcronymEndpoints() : AcronymEndpoints{
        return RetrofitFactory.provideRetrofit(
            AcronymEndpoints::class.java,
            BASE_URL
        )
    }

    @Singleton
    @Provides
    fun provideAcronymRepo(dispatcher: Dispatchers, retroObject: AcronymEndpoints): AcronymRepo{
        return AcronymRepoImpl(dispatcher, retroObject)
    }
}
