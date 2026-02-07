package com.themoviedb.di

import com.themoviedb.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppConfigModule {

    @Provides
    @Named("tmdb_api_key")
    fun provideApiKey(): String = BuildConfig.API_KEY

    @Provides
    @Named("tmdb_base_url")
    fun provideBaseUrl(): String = BuildConfig.BASE_URL
}
