package com.example.moviecataloguejetpackpro.common.di.presentation

import com.example.moviecataloguejetpackpro.data.source.remote.ApiService
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.FetchMovieUseCase
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.FetchTvShowUseCase
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun fetchMovieUseCase(apiService: ApiService) = FetchMovieUseCase(apiService)

    @Provides
    fun fetchTvShowUseCase(apiService: ApiService) = FetchTvShowUseCase(apiService)
}