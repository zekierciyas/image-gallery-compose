package com.zekierciyas.image_gallery_compose.ui.screens.screen1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zekierciyas.image_gallery_compose.domain.GetImagesUseCase
import com.zekierciyas.image_gallery_compose.domain.ImageUIModel
import com.zekierciyas.image_gallery_compose.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class Screen1ViewModel @Inject constructor(private val getImagesUseCase: GetImagesUseCase)
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