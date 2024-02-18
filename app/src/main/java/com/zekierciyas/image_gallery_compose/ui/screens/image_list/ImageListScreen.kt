package com.zekierciyas.image_gallery_compose.ui.screens.image_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zekierciyas.image_gallery_compose.R
import com.zekierciyas.image_gallery_compose.domain.model.ImageUIModel
import com.zekierciyas.image_gallery_compose.ui.component.ImageSearchBar
import com.zekierciyas.image_gallery_compose.util.DataState

@Composable
fun ImageListScreen(
    navController: NavHostController,
    viewModel: ImageListViewModel = hiltViewModel()
) {
    val collapseToolBar = remember { mutableStateOf(false) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                collapseToolBar.value = delta < 0 
                return Offset.Zero
            }
        }
    }

    Column{
       AnimatedVisibility(
            visible = !collapseToolBar.value,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut(),
            modifier = Modifier.animateContentSize()
        ) {
           Column {
               ExploreBar(
                   Modifier
                       .requiredHeight(100.dp)
                       .fillMaxWidth())

               ImageSearchBar(onSearch = {})
           }
       }

        Spacer(modifier = Modifier.height(10.dp))

        when(viewModel.state) {
            is DataState.Success -> {
                StaggeredGridWithImages(
                    modifier = Modifier.nestedScroll(
                        nestedScrollConnection
                    ),
                    images = (viewModel.state as DataState.Success<List<ImageUIModel>>).data,
                    imageClicked = {
                        navController.navigate("image_detail_screen/$it")
                    }
                )
            }

            is DataState.Error -> {
            }

            is DataState.Loading -> {
                StaggeredGridWithImages(
                    isLoading = true
                )
            }
        }
    }
}

@Composable
fun ExploreBar(modifier: Modifier) {
    Box(
        modifier = modifier
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_ring),
            contentDescription = "ring",
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .offset(
                    x = (-10).dp,
                    y = 10.dp
                )
                .requiredWidth(width = 30.dp)
                .requiredHeight(height = 30.dp)
        )

        Text(
            modifier = Modifier
                .align(alignment = Alignment.BottomStart)
                .offset(
                    x = 16.dp,
                    y = (-5).dp
                ),
            text = "Explore",
            lineHeight = 1.25.em,
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.7).sp)
        )
    }
}
