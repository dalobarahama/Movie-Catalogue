package com.example.moviecataloguejetpackpro.data.source.remote.usecase

import android.util.Log
import com.example.moviecataloguejetpackpro.data.source.local.entity.TrendingEntity
import com.example.moviecataloguejetpackpro.data.source.remote.ApiService
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchTrendingUseCase(private val apiService: ApiService) {

    sealed class Result {
        data class Success(val trendingList: List<TrendingEntity>) : Result()
        object Failure : Result()
    }

    suspend fun fetchTrending(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getTrending("all", "week")
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!.results)
                } else {
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    Log.d("fetchTrending", "catch: ${t.message}")
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }
        }
    }
}