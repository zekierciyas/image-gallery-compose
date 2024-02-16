package com.zekierciyas.image_gallery_compose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zekierciyas.image_gallery_compose.R

@Composable
fun ToolBar(title : String = "Title", backPressed: ()-> Unit = {}) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .requiredHeight(50.dp)
            .clickable {
                backPressed.invoke()
            }
            .fillMaxWidth()) {

        Icon(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = 10.dp),
            painter = painterResource(id = R.drawable.ic_back_press),
            contentDescription = "Vector - 0"
        )

        Text(
            text = title,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview("ToolBarPreview")
@Composable
fun ToolBarPreview() {
    ToolBar()
}
