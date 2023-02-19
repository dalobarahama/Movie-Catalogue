package com.example.moviecataloguejetpackpro.data.source.remote

import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchMovieUseCase(private val apiService: ApiService) {

    sealed class Result {
        data class Success(val movies: List<MovieEntity>) : Result()
        object Failure : Result()
    }

//    suspend fun fetchUpcomingMovies(): Result {
//        return withContext(Dispatchers.IO) {
//            try {
//                val response = apiService.getUpcomingMovies()
//                if (response.isSuccessful && response.body() != null) {
//                    return@withContext Result.Success(response.body()!!.movies)
//                } else {
//                    return@withContext Result.Failure
//                }
//            } catch (t: Throwable) {
//                if (t !is CancellationException) {
//                    return@withContext Result.Failure
//                } else {
//                    throw t
//                }
//            }
//        }
//    }
}