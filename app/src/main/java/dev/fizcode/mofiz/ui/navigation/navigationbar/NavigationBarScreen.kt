package dev.fizcode.mofiz.ui.navigation.navigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Movie
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: NavigationBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Movie
    )
    object Bookmark: NavigationBarScreen(
        route = "bookmark",
        title = "Bookmark",
        icon = Icons.Default.Bookmarks
    )
}