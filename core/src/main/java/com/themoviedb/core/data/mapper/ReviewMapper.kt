package com.themoviedb.core.data.mapper

import com.themoviedb.core.domain.model.Review
import com.themoviedb.core.network.model.ReviewDto

fun ReviewDto.toReview(): Review {
    return Review(
        id = id.orEmpty(),
        author = author.orEmpty(),
        content = content.orEmpty()
    )
}