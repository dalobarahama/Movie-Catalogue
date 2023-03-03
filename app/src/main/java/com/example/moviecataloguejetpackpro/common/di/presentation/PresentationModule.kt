package com.example.moviecataloguejetpackpro.common.di.presentation

import com.example.moviecataloguejetpackpro.data.source.remote.ApiService
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.*
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun fetchMovieUseCase(apiService: ApiService) = FetchMovieUseCase(apiService)

    @Provides
    fun fetchTvShowUseCase(apiService: ApiService) = FetchTvShowUseCase(apiService)

    @Provides
    fun fetchTrendingUseCase(apiService: ApiService) = FetchTrendingUseCase(apiService)

    @Provides
    fun fetchNowPlayingUseCase(apiService: ApiService) = FetchNowPlayingUseCase(apiService)

    @Provides
    fun fetchTvPopular(apiService: ApiService) = FetchTvPopularUseCase(apiService)
}