package dev.fizcode.mofiz.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.fizcode.mofiz.ui.components.HomeMovieLazyRow

@Composable
fun HomeScreen(
    navController: NavController,
    paddingValues: PaddingValues
) {

    val homeViewModel: HomeViewModel = hiltViewModel()

    // Bind viewModel
    homeViewModel.onViewLoaded()
    val nowPlaying = homeViewModel.shouldShowNowPlaying.collectAsState()
    val mostPopular = homeViewModel.shouldShowMostPopular.collectAsState()
    val upcoming = homeViewModel.shouldShowUpcoming.collectAsState()

    // View
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            HomeMovieLazyRow(
                cardHeader = "Now Playing 🍿",
                items = nowPlaying.value.results,
                navController = navController
            )

            HomeMovieLazyRow(
                cardHeader = "Most Popular ✨",
                items = mostPopular.value.results,
                navController = navController
            )

            HomeMovieLazyRow(
                cardHeader = "Upcoming ⬆️",
                items = upcoming.value.results,
                navController = navController
            )

        }
    }

}