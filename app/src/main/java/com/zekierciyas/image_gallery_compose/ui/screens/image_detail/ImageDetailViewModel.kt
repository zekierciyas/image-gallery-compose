package com.zekierciyas.image_gallery_compose.ui.screens.image_detail

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zekierciyas.image_gallery_compose.data.coroutine.WhileSubscribedOrRetained
import com.zekierciyas.image_gallery_compose.domain.usecase.GetImageByIDUseCase
import com.zekierciyas.image_gallery_compose.domain.model.ImageUIModel
import com.zekierciyas.image_gallery_compose.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getImageByIDUseCase: GetImageByIDUseCase,
): ViewModel() {

    private val userId: String? = savedStateHandle["id"]

    val state: StateFlow<DataState<ImageUIModel>> = getImageByIDUseCase
        .invoke(userId)
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribedOrRetained,
            initialValue = DataState.Loading
        )
}