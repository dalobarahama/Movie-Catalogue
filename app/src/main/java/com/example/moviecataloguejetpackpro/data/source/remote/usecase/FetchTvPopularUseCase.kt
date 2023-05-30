package com.example.moviecataloguejetpackpro.data.source.remote.usecase

import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.remote.ApiService
import com.example.moviecataloguejetpackpro.data.source.remote.response.Result
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchTvPopularUseCase(private val apiService: ApiService) {

    suspend fun fetchTvPopular(): Result<TVShowEntity> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getTVPopular()
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