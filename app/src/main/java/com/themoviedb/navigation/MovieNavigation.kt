package com.themoviedb.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.themoviedb.feature.genre.screen.GenreRoute
import com.themoviedb.movies.screen.MovieDetailRoute
import com.themoviedb.movies.screen.MoviesRoute

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "genre_list") {
        composable("genre_list") {
            GenreRoute(onGenreClick = { genreId, genreName ->
                navController.navigate("movie_list/$genreId/$genreName")
            })
        }
        composable("movie_list/{genreId}/{genreName}") { backStackEntry ->
            val genreId = backStackEntry.arguments?.getString("genreId")?.toIntOrNull() ?: return@composable
            val genreName = backStackEntry.arguments?.getString("genreName") ?: return@composable
            MoviesRoute(
                genreId = genreId,
                genreName = genreName,
                onMovieClick = { movieId ->
                    navController.navigate("movie_detail/$movieId")
                }
            )
        }
        composable("movie_detail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull() ?: return@composable
            MovieDetailRoute(
                movieId = movieId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}