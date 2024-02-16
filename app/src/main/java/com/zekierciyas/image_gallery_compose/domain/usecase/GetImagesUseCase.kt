package com.zekierciyas.image_gallery_compose.domain.usecase

import com.zekierciyas.image_gallery_compose.data.repository.ImageRepositoryImp
import com.zekierciyas.image_gallery_compose.domain.model.ImageUIModel
import com.zekierciyas.image_gallery_compose.domain.mapper.Mapper.asUIModel
import com.zekierciyas.image_gallery_compose.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(private val repository: ImageRepositoryImp) {
    operator fun invoke() : Flow<DataState<List<ImageUIModel>>> = flow {
        emit(DataState.Loading)
        delay(2000) //Mock delay to see data changes
        try {
            val result = repository.getAllImages().asUIModel()
            emit(DataState.Success(result))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}