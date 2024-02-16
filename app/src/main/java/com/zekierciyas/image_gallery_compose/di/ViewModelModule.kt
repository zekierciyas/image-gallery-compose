package com.zekierciyas.image_gallery_compose.di

import com.zekierciyas.image_gallery_compose.data.repository.ImageRepositoryImp
import com.zekierciyas.image_gallery_compose.domain.usecase.GetImageByIDUseCase
import com.zekierciyas.image_gallery_compose.domain.usecase.GetImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideGetImagesUseCase(
        repository: ImageRepositoryImp
    ): GetImagesUseCase {
        return GetImagesUseCase(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetImageByIDUseCase(
        repository: ImageRepositoryImp
    ): GetImageByIDUseCase {
        return GetImageByIDUseCase(
            repository = repository
        )
    }
}