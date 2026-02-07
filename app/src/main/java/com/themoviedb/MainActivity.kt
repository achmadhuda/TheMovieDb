package com.themoviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.themoviedb.core.ui.designsystem.MVTheme
import com.themoviedb.navigation.MovieNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val a = BuildConfig.API_KEY
        setContent {
            MVTheme {
                MovieNavigation()
            }
        }
    }
}