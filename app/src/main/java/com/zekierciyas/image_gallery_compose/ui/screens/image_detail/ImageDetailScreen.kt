package com.zekierciyas.image_gallery_compose.ui.screens.image_detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.zekierciyas.image_gallery_compose.domain.model.ImageUIModel
import com.zekierciyas.image_gallery_compose.ui.component.ToolBar
import com.zekierciyas.image_gallery_compose.ui.screens.image_list.shimmerEffect
import com.zekierciyas.image_gallery_compose.util.DataState

@Composable
fun ImageDetailScreen(
    navController: NavHostController,
    navBackStackEntry: NavBackStackEntry,
    viewModel: ImageDetailViewModel = hiltViewModel()) {

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        ToolBar(
            title = "Photo Detail",
            backPressed = {
                navController.popBackStack()
            })

        val state = viewModel.state.collectAsState()

        when(state.value) {
            is DataState.Success -> {
                AsyncImage(
                    model = (state.value as DataState.Success<ImageUIModel>).data.base64,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f)
                )
                ImageDetailDescription()
                RoundedBoxes()
                BottomButtons()
            }

            is DataState.Error -> {

            }

            is DataState.Loading -> {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .shimmerEffect())
                ImageDetailDescription(loadingEffect = true)
                RoundedBoxes(loadingEffect = true)
            }
        }
    }
}

@Composable
fun ImageDetailDescription(loadingEffect: Boolean = false) {

    val modifierText = if(loadingEffect) {
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .shimmerEffect()
            .offset(
                x = 20.dp,
                y = 10.dp
            )
    } else {
        Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .offset(
                x = 20.dp,
                y = 20.dp
            )
    }
    Text(
        modifier = modifierText,
        text = if (!loadingEffect)"Abstract Art Print" else "",
        color = Color(0xff121417),
        lineHeight = 1.25.em,
        style = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.33).sp)
    )

    Text(
        modifier = modifierText,
        text = if (!loadingEffect)"Landscape, abstract digital art print." else "",
        color = Color(0xff121417),
        lineHeight = 1.5.em,
        style = TextStyle(
            fontSize = 16.sp))

    Text(
        modifier = modifierText,
        text = if (!loadingEffect)"Printed on 300gsm white textured stock." else "",
        color = Color(0xff121417),
        lineHeight = 1.5.em,
        style = TextStyle(
            fontSize = 16.sp))
}

@Composable
fun RoundedBoxes(loadingEffect: Boolean = false) {
    val whichBoxSelected = remember {
        mutableStateOf(SelectedBox.FIRST)
    }
    val boxModifier = if (loadingEffect) {
        Modifier
            .padding(10.dp)
            .shimmerEffect()
            .border(
                border = BorderStroke(1.dp, Color.DarkGray),
                shape = ShapeDefaults.Medium
            )
            .size(height = 50.dp, width = 100.dp) }
    else {
        Modifier
            .padding(10.dp)
            .border(
                border = BorderStroke(1.dp, Color.DarkGray),
                shape = ShapeDefaults.Medium
            )
            .size(height = 50.dp, width = 100.dp)
    }
    Column(modifier = Modifier
        .padding(10.dp)
        .wrapContentHeight()
        .fillMaxWidth()
    ) {
        Row {
            Box(
                contentAlignment = Alignment.Center,
                modifier = boxModifier
                    .clickable {
                        whichBoxSelected.value = SelectedBox.FIRST
                    }
                    .border(
                        border = if (whichBoxSelected.value == SelectedBox.FIRST
                            && !loadingEffect
                        ) BorderStroke(3.dp, Color.DarkGray)
                        else BorderStroke(1.dp, Color.DarkGray),
                        shape = ShapeDefaults.Medium
                    )
            ) {
                if (!loadingEffect) Text(text = "8x10in")
                else Text(text = "...")
            }

            Box(contentAlignment = Alignment.Center,
                modifier = boxModifier
                    .clickable {
                        whichBoxSelected.value = SelectedBox.SECOND
                    }
                    .border(
                        border = if (whichBoxSelected.value == SelectedBox.SECOND) BorderStroke(
                            3.dp,
                            Color.DarkGray
                        )
                        else BorderStroke(1.dp, Color.DarkGray),
                        shape = ShapeDefaults.Medium
                    ),
                ) {
               if (!loadingEffect) Text(text = "11x14in")
               else Text(text = "...")
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = boxModifier
                    .clickable {
                        whichBoxSelected.value = SelectedBox.LAST
                    }
                    .border(
                        border = if (whichBoxSelected.value == SelectedBox.LAST) BorderStroke(
                            3.dp,
                            Color.DarkGray
                        )
                        else BorderStroke(1.dp, Color.DarkGray),
                        shape = ShapeDefaults.Medium
                    ),
            ) {
                if (!loadingEffect) Text(text = "16x20in")
                else Text(text = "...")
            }
        }
    }
}

@Composable
fun BottomButtons() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDBE0E5)),
                shape = ShapeDefaults.Large,
                onClick = {

                },
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f)
                    .padding(end = 5.dp)
            ) {
                Text("Add to Bag", color = Color.Black, fontWeight = FontWeight.Bold)
            }
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A80E5)),
                shape = ShapeDefaults.Large,
                onClick = {

                },
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f)
                    .padding(start = 5.dp)
            ) {
                Text("Buy Now", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview("RoundedBoxesPreview")
@Composable()
fun RoundedBoxesPreview() {
    RoundedBoxes()
}

enum class SelectedBox {
    FIRST,
    SECOND,
    LAST,
}
