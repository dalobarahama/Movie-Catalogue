package com.example.moviecataloguejetpackpro.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecataloguejetpackpro.BuildConfig
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.remote.ApiConfig
import com.example.moviecataloguejetpackpro.data.source.remote.response.MovieResponse
import com.example.moviecataloguejetpackpro.data.source.remote.response.TVShowResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JsonHelper {

    companion object {
        private const val TAG = "JsonHelper"
        private const val API_KEY = BuildConfig.API_KEY
    }

    private val _movieList = MutableLiveData<List<MovieEntity>>()
    val movieList: LiveData<List<MovieEntity>> = _movieList

    private val _tvShowList = MutableLiveData<List<TVShowEntity>>()
    val tvShowList: LiveData<List<TVShowEntity>> = _tvShowList

    init {
        getMovieApi()
        getTVShowApi()
    }

    private fun getMovieApi() {
        val client = ApiConfig.getApiService(API_KEY).getUpcomingMovies()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                movieResponse: Response<MovieResponse>,
            ) {
                if (movieResponse.isSuccessful) {
                    _movieList.value = movieResponse.body()?.movies
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
        val client = ApiConfig.getApiService(API_KEY).getTVOnTheAir()
        client.enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(
                call: Call<TVShowResponse>,
                response: Response<TVShowResponse>,
            ) {
                if (response.isSuccessful) {
                    _tvShowList.value = response.body()?.tvShows
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}