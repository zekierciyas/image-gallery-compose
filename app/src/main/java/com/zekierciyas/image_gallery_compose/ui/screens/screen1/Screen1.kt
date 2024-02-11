package com.zekierciyas.image_gallery_compose.ui.screens.screen1

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.zekierciyas.image_gallery_compose.domain.ImageUIModel
import com.zekierciyas.image_gallery_compose.util.DataState

@Composable
fun Screen1(navController: NavHostController, viewModel: Screen1ViewModel) {
    Column {
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
        Text(text = "ID is ${viewModel.id}")

        Button(onClick = { navController.navigate("screen2/${viewModel.id}") }) {
            Text(text = "Go to Screen 2")
        }
        Button(onClick = { viewModel.id++ }) {
            Text(text = "Increment ID")
        }
    }
}