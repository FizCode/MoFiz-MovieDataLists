package dev.fizcode.mofiz.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun StatusAndNavBarColorBackground() {
    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    if (isDarkTheme) {
        systemUiController.setSystemBarsColor(color = MaterialTheme.colorScheme.background,darkIcons = false)
        systemUiController.setNavigationBarColor(color = MaterialTheme.colorScheme.surface,darkIcons = false)
    } else {
        systemUiController.setStatusBarColor(color = MaterialTheme.colorScheme.background,darkIcons = true)
        systemUiController.setNavigationBarColor(color = MaterialTheme.colorScheme.surface,darkIcons = true)
    }
}

@Composable
fun StatusColorBackgroundAndNavBarColorSurfaceContainer() {
    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    if (isDarkTheme) {
        systemUiController.setSystemBarsColor(color = MaterialTheme.colorScheme.background,darkIcons = false)
        systemUiController.setNavigationBarColor(color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),darkIcons = false)
    } else {
        systemUiController.setStatusBarColor(color = MaterialTheme.colorScheme.background,darkIcons = true)
        systemUiController.setNavigationBarColor(color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),darkIcons = true)
    }
}

@Composable
fun StatusAndNavbarTransparent(){
    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    if (isDarkTheme) {
        systemUiController.setSystemBarsColor(color = Color.Blue,darkIcons = false)
        systemUiController.setNavigationBarColor(color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),darkIcons = false)
    } else {
        systemUiController.setStatusBarColor(color = Color.Transparent,darkIcons = true)
        systemUiController.setNavigationBarColor(color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),darkIcons = true)
    }

}