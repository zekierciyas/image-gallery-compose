package com.zekierciyas.image_gallery_compose.ui.screens.screen2

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.zekierciyas.image_gallery_compose.util.DataState

@Composable
fun Screen2(navController: NavHostController, navBackStackEntry: NavBackStackEntry, viewModel: Screen2ViewModel) {
    val id = navBackStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
    LaunchedEffect(key1 = 0) {
        viewModel.getImage(id.toString())
    }
    Column {
        Text(text = "Screen 2 with ID: $id")
        when(viewModel.state) {
            is DataState.Success -> {
                Text(text = "State is succeed")
            }

            is DataState.Error -> {
                Text(text = "State is failed")
            }

            is DataState.Loading -> {
                Text(text = "State is loading")
            }
        }
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Go to Screen 1")
        }
    }
}