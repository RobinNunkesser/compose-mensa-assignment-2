package de.hshl.isd.mensa

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int? = null,
    val icon: ImageVector? = null
) {
    object Mensa : Screen("mensa", R.string.nav_item_mensa, Icons.Filled.Info)
    object Settings :
        Screen("settings", R.string.nav_item_settings, Icons.Filled.Settings)
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: String = Screen.Mensa.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Mensa.route) {
            Mensa(navController = navController)
        }
        composable(Screen.Settings.route) {
            Settings(navController = navController)
        }
    }
}