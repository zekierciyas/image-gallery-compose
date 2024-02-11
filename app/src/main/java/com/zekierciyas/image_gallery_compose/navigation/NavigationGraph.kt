package com.zekierciyas.image_gallery_compose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zekierciyas.image_gallery_compose.ui.screens.screen1.Screen1
import com.zekierciyas.image_gallery_compose.ui.screens.screen1.Screen1ViewModel
import com.zekierciyas.image_gallery_compose.ui.screens.screen2.Screen2
import com.zekierciyas.image_gallery_compose.ui.screens.screen2.Screen2ViewModel

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    val screen1ViewModel: Screen1ViewModel = hiltViewModel()
    val screen2ViewModel: Screen2ViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = ScreenRoutes.SCREEN_1) {
        composable(ScreenRoutes.SCREEN_1) {
            Screen1(
                navController = navController,
                viewModel = screen1ViewModel
            )
        }
        composable(ScreenRoutes.SCREEN_2) { backStackEntry ->
            Screen2(
                navController = navController,
                viewModel = screen2ViewModel,
                navBackStackEntry = backStackEntry
            )
        }
    }
}