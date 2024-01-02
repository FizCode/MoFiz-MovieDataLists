package dev.fizcode.mofiz.ui.screens.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.fizcode.mofiz.common.Constant
import dev.fizcode.mofiz.ui.components.MovieListCardsSmall
import dev.fizcode.mofiz.ui.navigation.navgraph.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    navController: NavController
){
    // Manually set status bar color
    PrimaryStatusBarColorsTheme()
    // Reset status bar color to Transparent on dispose
    ResetStatusBarColorThemeOnDispose()

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    // Bind ViewModel
    val bookmarkViewModel: BookmarkViewModel = hiltViewModel()
    val bookmarks = bookmarkViewModel.shouldShowBookmarks.collectAsState()
    bookmarkViewModel.onViewLoaded()

    // User Interface
    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = {
                    Text(text = "Bookmark")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                columns = GridCells.FixedSize(98.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(
                    items = bookmarks.value
                ) { _, item ->
                    item.title?.let { title ->
                        MovieListCardsSmall(
                            movieTitle = title,
                            moviePoster = Constant.Named.IMAGE_URL + item.posterPath,
                            onClick = {
                                item.movieId?.let { movieId ->
                                    navController.navigate(route = Screen.Details.passId(movieId))
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

// Set status bar color manually
@Composable
private fun PrimaryStatusBarColorsTheme() {
    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    if (isDarkTheme) {
        systemUiController.setSystemBarsColor(color = MaterialTheme.colorScheme.primary,darkIcons = true)
    } else {
        systemUiController.setStatusBarColor(color = MaterialTheme.colorScheme.primary, darkIcons = false)
    }
}

// Remove status bar color that already set manually on dispose
@Composable
private fun ResetStatusBarColorThemeOnDispose() {
    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController) {
        onDispose {
            if (isDarkTheme) {
                systemUiController.setSystemBarsColor(color = Color.Transparent,darkIcons = false)
            } else {
                systemUiController.setStatusBarColor(color = Color.Transparent, darkIcons = true)
            }
        }
    }
}