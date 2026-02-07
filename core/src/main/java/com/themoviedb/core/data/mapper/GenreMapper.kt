package com.themoviedb.core.data.mapper

import com.themoviedb.core.domain.model.Genre
import com.themoviedb.core.network.model.GenreDto

fun GenreDto.toGenre(): Genre {
    return Genre(
        id = id ?: 0,
        name = name.orEmpty()
    )
}