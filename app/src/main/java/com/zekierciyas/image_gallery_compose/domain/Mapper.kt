package com.zekierciyas.image_gallery_compose.domain

import com.zekierciyas.image_gallery_compose.data.model.ImageListResponse
import com.zekierciyas.image_gallery_compose.data.model.ImageResponse

object Mapper {
    fun ImageListResponse?.asUIModel(): List<ImageUIModel> {
        val imageUiModelList = arrayListOf<ImageUIModel>()
        this?.imageResponses?.forEach {
            imageUiModelList.add(
                ImageUIModel(
                    base64 = it?.base64,
                    description = it?.description,
                    title = it?.title
                )
            )
        }
        return imageUiModelList
    }

    fun ImageResponse?.asUIModel(): ImageUIModel {
        return ImageUIModel(
            base64 = this?.base64,
            description = this?.description,
            title = this?.title
        )
    }
}