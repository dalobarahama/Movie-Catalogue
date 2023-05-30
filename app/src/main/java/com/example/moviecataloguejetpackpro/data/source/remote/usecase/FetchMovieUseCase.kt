package com.example.moviecataloguejetpackpro.data.source.remote.usecase

import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.remote.ApiService
import com.example.moviecataloguejetpackpro.data.source.remote.response.Result
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchMovieUseCase(private val apiService: ApiService) {

    suspend fun fetchUpcomingMovies(): Result<MovieEntity> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getUpcomingMovies()
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!.movies)
                } else {
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }
        }
    }
}