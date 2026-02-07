package com.themoviedb.core.data.mapper

import com.themoviedb.core.domain.model.Video
import com.themoviedb.core.network.model.VideoDto

fun VideoDto.toVideo(): Video {
    return Video(
        key = key.orEmpty(),
        name = name.orEmpty(),
        site = site.orEmpty(),
        type = type.orEmpty()
    )
}