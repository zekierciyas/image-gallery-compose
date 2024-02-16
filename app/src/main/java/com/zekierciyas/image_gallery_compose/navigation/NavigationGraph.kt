package com.zekierciyas.image_gallery_compose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zekierciyas.image_gallery_compose.ui.screens.screen1.ImageListScreen
import com.zekierciyas.image_gallery_compose.ui.screens.screen1.ImageListViewModel
import com.zekierciyas.image_gallery_compose.ui.screens.screen2.ImageDetailScreen
import com.zekierciyas.image_gallery_compose.ui.screens.screen2.ImageDetailViewModel

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    val imageListViewModel: ImageListViewModel = hiltViewModel()
    val imageDetailViewModel: ImageDetailViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = ScreenRoutes.IMAGE_LIST_SCREEN) {
        composable(ScreenRoutes.IMAGE_LIST_SCREEN) {
            ImageListScreen(
                navController = navController,
                viewModel = imageListViewModel
            )
        }
        composable(ScreenRoutes.IMAGE_DETAIL_SCREEN) { backStackEntry ->
            ImageDetailScreen(
                navController = navController,
                viewModel = imageDetailViewModel,
                navBackStackEntry = backStackEntry
            )
        }
    }
}