package com.zekierciyas.image_gallery_compose.data.repository

import com.zekierciyas.image_gallery_compose.data.api.ApiService
import com.zekierciyas.image_gallery_compose.data.model.ImageResponse
import com.zekierciyas.image_gallery_compose.data.model.ImageListResponse
import com.zekierciyas.image_gallery_compose.domain.repository.ImageRepository
import javax.inject.Inject

class ImageRepositoryImp @Inject constructor(
    private val apiService: ApiService
): ImageRepository {
    override suspend fun getAllImages(): ImageListResponse {
        return apiService.allImages()
    }

    override suspend fun getImage(id: String): ImageResponse {
        return apiService.image(id = id)
    }
}