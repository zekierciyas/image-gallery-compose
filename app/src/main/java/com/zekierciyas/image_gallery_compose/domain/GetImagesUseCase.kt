package com.zekierciyas.image_gallery_compose.domain

import com.zekierciyas.image_gallery_compose.data.repository.ImageRepositoryImp
import com.zekierciyas.image_gallery_compose.domain.Mapper.asUIModel
import com.zekierciyas.image_gallery_compose.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onCompletion
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