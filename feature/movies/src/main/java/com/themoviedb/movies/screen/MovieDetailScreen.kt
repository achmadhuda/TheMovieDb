package com.themoviedb.movies.screen

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.themoviedb.core.domain.model.Movie
import com.themoviedb.core.domain.model.Review
import com.themoviedb.core.domain.model.UiState
import com.themoviedb.core.ui.designsystem.MVTheme
import com.themoviedb.movies.viewmodel.MoviesViewModel

@Composable
fun MovieDetailRoute(
    movieId: Int,
    onBackClick: () -> Unit,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val lazyReviewItems = viewModel.getReviews(movieId).collectAsLazyPagingItems()

    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId)
    }

    MovieDetailScreen(
        uiState = uiState,
        lazyReviewItems = lazyReviewItems,
        onBackClick = onBackClick,
        onPlayTrailer = { key ->
            val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$key"))
            val webIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/watch?v=$key")
            )

            try {
                context.startActivity(appIntent)
            } catch (_: ActivityNotFoundException) {
                context.startActivity(webIntent)
            }
        },
        onLoadTrailer = { onResult ->
            viewModel.getTrailerKey(movieId, onResult)
        }
    )
}

@Composable
fun MovieDetailScreen(
    uiState: UiState<Movie>,
    lazyReviewItems: androidx.paging.compose.LazyPagingItems<Review>,
    onBackClick: () -> Unit,
    onPlayTrailer: (String) -> Unit,
    onLoadTrailer: ((String?) -> Unit) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movie Detail") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->

        when (uiState) {
            is UiState.Loading -> { /* ... */ }

            is UiState.Error -> { /* ... */ }

            is UiState.Success -> {
                val movie = uiState.data

                LazyColumn(modifier = Modifier.padding(padding)) {

                    item {
                        Column(modifier = Modifier.padding(16.dp)) {
                            AsyncImage(
                                model = movie.backdropPath?.let {
                                    "https://image.tmdb.org/t/p/w780$it"
                                },
                                contentDescription = movie.title,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(220.dp),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(movie.title, style = MVTheme.typography.heading1Regular)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(movie.overview.orEmpty())
                            Spacer(modifier = Modifier.height(12.dp))

                            Button(
                                onClick = {
                                    onLoadTrailer { key ->
                                        key?.let(onPlayTrailer)
                                    }
                                }
                            ) {
                                Text("Play Trailer")
                            }
                        }
                    }

                    item {
                        Text(
                            text = "User Reviews",
                            style = MVTheme.typography.heading1Regular,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    items(lazyReviewItems.itemCount) { index ->
                        lazyReviewItems[index]?.let { review ->
                            ReviewItem(review)
                        }
                    }

                    when (val state = lazyReviewItems.loadState.append) {
                        is LoadState.Loading -> item {
                            Box(
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }

                        is LoadState.Error -> item {
                            Text(
                                text = state.error.message ?: "Failed to load more reviews",
                                color = MVTheme.colors.primary200,
                                modifier = Modifier.padding(16.dp)
                            )
                        }

                        else -> Unit
                    }
                }
            }
        }
    }
}

@Composable
fun ReviewItem(review: Review) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = review.author,
            style = MVTheme.typography.heading1Medium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = review.content,
            style = MVTheme.typography.body1Medium
        )
    }
}