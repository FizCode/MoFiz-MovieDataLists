package dev.fizcode.mofiz.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.fizcode.mofiz.ui.components.CustomSearchInput
import dev.fizcode.mofiz.ui.components.HomeMovieBigLazyRow
import dev.fizcode.mofiz.ui.components.HomeMovieSmallLazyRow
import dev.fizcode.mofiz.ui.components.StatusColorBackgroundAndNavBarColorSurfaceContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    paddingValues: PaddingValues
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val homeViewModel: HomeViewModel = hiltViewModel()

    // Bind viewModel
    homeViewModel.onViewLoaded()
    val nowPlaying = homeViewModel.shouldShowNowPlaying.collectAsState()
    val mostPopular = homeViewModel.shouldShowMostPopular.collectAsState()
    val upcoming = homeViewModel.shouldShowUpcoming.collectAsState()


    // View
    StatusColorBackgroundAndNavBarColorSurfaceContainer()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.padding(end = 16.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        CustomSearchInput()
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .imePadding()
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        ) {
            HomeMovieBigLazyRow(
                cardHeader = "Now Playing 🍿",
                items = nowPlaying.value.results,
                navController = navController
            )

            HomeMovieSmallLazyRow(
                cardHeader = "Most Popular ✨",
                items = mostPopular.value.results,
                navController = navController
            )

            HomeMovieSmallLazyRow(
                cardHeader = "Upcoming ⬆️",
                items = upcoming.value.results,
                navController = navController
            )

            HomeMovieSmallLazyRow(
                cardHeader = "Upcoming ⬆️",
                items = upcoming.value.results,
                navController = navController
            )

            HomeMovieSmallLazyRow(
                cardHeader = "Upcoming ⬆️",
                items = upcoming.value.results,
                navController = navController
            )

            HomeMovieSmallLazyRow(
                cardHeader = "Upcoming ⬆️",
                items = upcoming.value.results,
                navController = navController
            )

        }
    }

}