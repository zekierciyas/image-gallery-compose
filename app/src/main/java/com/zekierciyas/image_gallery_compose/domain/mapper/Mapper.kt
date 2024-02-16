package com.zekierciyas.image_gallery_compose.domain.mapper

import com.zekierciyas.image_gallery_compose.data.model.ImageListResponse
import com.zekierciyas.image_gallery_compose.data.model.ImageResponse
import com.zekierciyas.image_gallery_compose.domain.model.ImageUIModel

object Mapper {
    fun ImageListResponse?.asUIModel(): List<ImageUIModel> {
        val imageUiModelList = arrayListOf<ImageUIModel>()
        this?.imageResponses?.forEach {
            imageUiModelList.add(
                ImageUIModel(
                    base64 = it?.base64,
                    description = it?.description,
                    title = it?.title,
                    id = it?.id.toString()
                )
            )
        }
        return imageUiModelList
    }

    fun ImageResponse?.asUIModel(): ImageUIModel {
        return ImageUIModel(
            base64 = this?.base64,
            description = this?.description,
            title = this?.title,
            id = this?.id.toString()
        )
    }
}