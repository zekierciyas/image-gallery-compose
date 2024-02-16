package com.zekierciyas.image_gallery_compose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zekierciyas.image_gallery_compose.ui.screens.image_list.ImageListScreen
import com.zekierciyas.image_gallery_compose.ui.screens.image_list.ImageListViewModel
import com.zekierciyas.image_gallery_compose.ui.screens.image_detail.ImageDetailScreen
import com.zekierciyas.image_gallery_compose.ui.screens.image_detail.ImageDetailViewModel

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.IMAGE_LIST_SCREEN
    ) {
        composable(ScreenRoutes.IMAGE_LIST_SCREEN) {
            ImageListScreen(
                navController = navController
            )
        }
        composable(
           route = ScreenRoutes.IMAGE_DETAIL_SCREEN,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            ImageDetailScreen(
                navController = navController,
                navBackStackEntry = backStackEntry
            )
        }
    }
}