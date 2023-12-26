package dev.fizcode.mofiz.ui.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import dev.fizcode.mofiz.ui.navigation.navigationbar.MainScreen
import dev.fizcode.mofiz.ui.screens.details.DetailsScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Home.route,
        route = HOME_ROUTE
    ) {
        composable(
            route = Screen.Home.route
        ) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            it.arguments?.getInt("id")
                ?.let { id -> DetailsScreen(navController = navController, argsId = id) }
        }
    }
}