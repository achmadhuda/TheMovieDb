package com.themoviedb.core.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themoviedb.core.data.mapper.toReview
import com.themoviedb.core.domain.model.Review
import com.themoviedb.core.network.service.TmdbApiService
import retrofit2.HttpException
import java.io.IOException

class ReviewPagingSource(
    private val apiService: TmdbApiService,
    private val movieId: Int
) : PagingSource<Int, Review>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> =
        runCatching {
            val page = params.key ?: 1
            val response = apiService.getMovieReviews(movieId, page)
            val reviews = response.body()?.reviews?.map { it.toReview() }.orEmpty()

            LoadResult.Page(
                data = reviews,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (reviews.isEmpty()) null else page + 1
            )
        }.getOrElse { throwable ->
            when (throwable) {
                is IOException, is HttpException -> LoadResult.Error(throwable)
                else -> LoadResult.Error(throwable)
            }
        }

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? =
        state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.let { page ->
                page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
            }
        }
}