package com.themoviedb.core.data.mapper

import com.themoviedb.core.domain.model.Movie
import com.themoviedb.core.network.model.MovieDto

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id ?: 0,
        title = title.orEmpty(),
        overview = overview.orEmpty(),
        posterPath = posterPath.orEmpty(),
        backdropPath = backdropPath.orEmpty(),
        voteAverage = voteAverage ?: 0.0,
        releaseDate = releaseDate.orEmpty()
    )
}