package com.zekierciyas.image_gallery_compose.data.model


import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("base64")
    val base64: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?
)