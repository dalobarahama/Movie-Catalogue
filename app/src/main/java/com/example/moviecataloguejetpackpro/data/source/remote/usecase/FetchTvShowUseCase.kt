package com.example.moviecataloguejetpackpro.data.source.remote.usecase

import android.util.Log
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.remote.ApiService
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchTvShowUseCase(private val apiService: ApiService) {

    sealed class Result {
        data class Success(val tvShows: List<TVShowEntity>) : Result()
        object Failure : Result()
    }

    suspend fun fetchTvOnTheAir(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getTVOnTheAir()
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!.tvShows)
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