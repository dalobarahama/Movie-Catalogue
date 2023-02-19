package com.example.moviecataloguejetpackpro.utils

import android.util.Log
import com.example.moviecataloguejetpackpro.BuildConfig
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.remote.ApiConfig
import com.example.moviecataloguejetpackpro.data.source.remote.response.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JsonHelper {

    companion object {
        private const val TAG = "JsonHelper"
        private const val API_KEY = BuildConfig.API_KEY
    }

    val movieList = ArrayList<MovieEntity>()

    val tvShowList = ArrayList<TVShowEntity>()

    init {
        getTVShowApi()
        getMovieApi()
    }

    private fun getMovieApi() {
        val client = ApiConfig.getApiService(API_KEY).getUpcomingMovies()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                movieResponse: Response<MovieResponse>,
            ) {
                if (movieResponse.isSuccessful) {
                    Log.i(TAG, "onResponse: movieResponse success")
                    val listArray = movieResponse.body()?.movies
                    if (listArray != null) {
                        for (i in 0 until listArray.lastIndex) {
                            val movie = listArray[i]
                            movieList.add(movie)
                        }
                    }
                } else {
                    Log.e(TAG, "onResponse: ${movieResponse.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    private fun getTVShowApi() {
//        val client = ApiConfig.getApiService(API_KEY).getTVOnTheAir()
//        client.enqueue(object : Callback<TVShowResponse> {
//            override fun onResponse(
//                call: Call<TVShowResponse>,
//                response: Response<TVShowResponse>,
//            ) {
//                if (response.isSuccessful) {
//                    Log.i(TAG, "onResponse: movieResponse success")
//                    val listArray = response.body()?.tvShows
//                    if (listArray != null) {
//                        for (i in 0 until listArray.lastIndex) {
//                            val tvShow = listArray[i]
//                            tvShowList.add(tvShow)
//                        }
//                    }
//                } else {
//                    Log.e(TAG, "onResponse: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//
//        })
    }
}