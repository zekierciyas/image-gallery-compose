package com.zekierciyas.image_gallery_compose.di

import com.zekierciyas.image_gallery_compose.data.repository.ImageRepositoryImp
import com.zekierciyas.image_gallery_compose.domain.GetImageByIDUseCase
import com.zekierciyas.image_gallery_compose.domain.GetImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGetImagesUseCase(
        repository: ImageRepositoryImp
    ): GetImagesUseCase {
        return GetImagesUseCase(
            repository = repository
        )
    }

    @Singleton
    @Provides
    fun provideGetImageByIDUseCase(
        repository: ImageRepositoryImp
    ): GetImageByIDUseCase {
        return GetImageByIDUseCase(
            repository = repository
        )
    }

}