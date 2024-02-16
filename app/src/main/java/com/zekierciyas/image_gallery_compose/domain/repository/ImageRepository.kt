package com.zekierciyas.image_gallery_compose.domain.repository

import com.zekierciyas.image_gallery_compose.data.model.ImageResponse
import com.zekierciyas.image_gallery_compose.data.model.ImageListResponse
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    suspend fun getAllImages(): ImageListResponse

    suspend fun getImage(id: String): ImageResponse
}