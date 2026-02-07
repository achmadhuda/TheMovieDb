package com.themoviedb.core.di

import com.themoviedb.core.data.repository.GenreRepository
import com.themoviedb.core.data.repository.GenreRepositoryImpl
import com.themoviedb.core.data.repository.MovieRepository
import com.themoviedb.core.data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(
        impl: MovieRepositoryImpl
    ): MovieRepository

    @Binds abstract fun bindGenreRepository(
        impl: GenreRepositoryImpl
    ): GenreRepository
}