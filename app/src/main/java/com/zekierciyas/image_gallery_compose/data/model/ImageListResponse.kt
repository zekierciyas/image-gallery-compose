package com.zekierciyas.image_gallery_compose.data.model


import com.google.gson.annotations.SerializedName

data class ImageListResponse(
    @SerializedName("images")
    val imageResponses: List<ImageResponse?>?
)