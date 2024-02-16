package com.zekierciyas.image_gallery_compose.ui.screens.image_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zekierciyas.image_gallery_compose.domain.usecase.GetImagesUseCase
import com.zekierciyas.image_gallery_compose.domain.model.ImageUIModel
import com.zekierciyas.image_gallery_compose.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(private val getImagesUseCase: GetImagesUseCase)
    : ViewModel() {

    var id by mutableIntStateOf(0)
    var state : DataState<List<ImageUIModel>> by mutableStateOf(DataState.Loading)

    init {
        viewModelScope.launch {
            getImagesUseCase.invoke().collectLatest {
                state = it
            }
        }
    }
}