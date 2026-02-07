package com.themoviedb.feature.genre.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.themoviedb.core.domain.model.Genre
import com.themoviedb.core.domain.model.UiState
import com.themoviedb.core.ui.designsystem.MVTheme
import com.themoviedb.feature.genre.viewmodel.GenreViewModel

@Composable
fun GenreRoute(
    onGenreClick: (Int, String) -> Unit,
    viewModel: GenreViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getGenres()
    }

    GenreScreen(
        uiState = uiState,
        onGenreClick = onGenreClick
    )
}

@Composable
fun GenreScreen(
    uiState: UiState<List<Genre>>,
    onGenreClick: (Int, String) -> Unit
) {
    when (uiState) {
        is UiState.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        is UiState.Success -> LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(uiState.data) { genre ->
                GenreItem(
                    genre = genre,
                    onClick = { onGenreClick(genre.id, genre.name) }
                )
            }
        }

        is UiState.Error -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Error: ${uiState.message}",color = MVTheme.colors.primary200)
        }
    }
}

@Composable
fun GenreItem(
    genre: Genre,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = genre.name,
            modifier = Modifier.padding(16.dp),
            style =
                MVTheme.typography.heading1Regular
        )
    }
}