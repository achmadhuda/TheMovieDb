package com.themoviedb.core.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themoviedb.core.data.mapper.toMovie
import com.themoviedb.core.domain.model.Movie
import com.themoviedb.core.network.service.TmdbApiService

class MoviePagingSource(
    private val apiService: TmdbApiService,
    private val genreId: Int
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> =
        runCatching {
            val page = params.key ?: 1
            val response = apiService.discoverMovies(genreId, page)
            val movies = response.body()?.movies.orEmpty().map { it.toMovie() }

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        }.getOrElse { throwable ->
            LoadResult.Error(throwable)
        }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? =
        state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.let { page ->
                page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
            }
        }
}