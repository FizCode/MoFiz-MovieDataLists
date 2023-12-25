package dev.fizcode.mofiz.ui.navigation.navgraph

const val ROOT_ROUTE = "root"
const val HOME_ROUTE = "home"
const val DETAILS_ARGUMENT_KEY = "id"

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object Details: Screen("movie_details_screen/{$DETAILS_ARGUMENT_KEY}") {
        fun passId(id: Int): String {
            return this.route.replace(oldValue = "{$DETAILS_ARGUMENT_KEY}", newValue = id.toString())
        }
    }

}