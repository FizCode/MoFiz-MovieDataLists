package dev.fizcode.mofiz.ui.navigation.navigationbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.fizcode.mofiz.ui.screens.bookmark.BookmarkScreen
import dev.fizcode.mofiz.ui.screens.home.HomeScreen

@Composable
fun BottomNavGraph(navController: NavController, bottomNavController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = bottomNavController,
        startDestination = NavigationBarScreen.Home.route
    ) {
        composable(route = NavigationBarScreen.Home.route) {
            HomeScreen(navController = navController, paddingValues = paddingValues)
        }
        composable(route = NavigationBarScreen.Bookmark.route) {
            BookmarkScreen()
        }
    }
}