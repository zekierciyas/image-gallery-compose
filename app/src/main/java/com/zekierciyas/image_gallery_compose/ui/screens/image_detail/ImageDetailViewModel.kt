package com.zekierciyas.image_gallery_compose.ui.screens.image_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zekierciyas.image_gallery_compose.domain.usecase.GetImageByIDUseCase
import com.zekierciyas.image_gallery_compose.domain.model.ImageUIModel
import com.zekierciyas.image_gallery_compose.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getImageByIDUseCase: GetImageByIDUseCase,
): ViewModel() {

    private val userId: String? = savedStateHandle["id"]

    var state : DataState<ImageUIModel> by mutableStateOf(DataState.Loading)
    var id by mutableIntStateOf(0)

    fun getImage() {
        viewModelScope.launch {
            getImageByIDUseCase.invoke(userId).collectLatest {
                state = it
            }
        }
    }
}