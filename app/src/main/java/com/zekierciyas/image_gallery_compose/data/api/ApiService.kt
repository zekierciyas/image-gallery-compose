package com.zekierciyas.image_gallery_compose.data.api

import com.zekierciyas.image_gallery_compose.data.model.ImageResponse
import com.zekierciyas.image_gallery_compose.data.model.ImageListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("images")
    suspend fun allImages(): ImageListResponse

    @GET("{id}")
    suspend fun image(@Path("id") id: String): ImageResponse
}