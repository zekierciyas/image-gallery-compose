package com.zekierciyas.image_gallery_compose.domain

import com.google.gson.annotations.SerializedName

data class ImageUIModel(
    @SerializedName("base64")
    val base64: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("title")
    val title: String?
)
