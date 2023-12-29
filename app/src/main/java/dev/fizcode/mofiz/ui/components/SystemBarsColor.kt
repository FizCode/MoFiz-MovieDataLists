package dev.fizcode.mofiz.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
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