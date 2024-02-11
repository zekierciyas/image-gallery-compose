package com.zekierciyas.image_gallery_compose.ui.screens.screen2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zekierciyas.image_gallery_compose.domain.GetImageByIDUseCase
import com.zekierciyas.image_gallery_compose.domain.ImageUIModel
import com.zekierciyas.image_gallery_compose.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Screen2ViewModel @Inject constructor(private val getImageByIDUseCase: GetImageByIDUseCase):
    ViewModel() {

    var state : DataState<ImageUIModel> by mutableStateOf(DataState.Loading)
    var id by mutableIntStateOf(0)

    fun getImage(id: String) {
        viewModelScope.launch {
            getImageByIDUseCase.invoke(id).collectLatest {
                state = it
            }
        }
    }
}