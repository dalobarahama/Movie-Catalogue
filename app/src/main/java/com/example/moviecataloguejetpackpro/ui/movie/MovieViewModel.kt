package com.example.moviecataloguejetpackpro.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecataloguejetpackpro.BuildConfig
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.remote.ApiConfig
import com.example.moviecataloguejetpackpro.data.source.remote.Movie
import com.example.moviecataloguejetpackpro.data.source.remote.MovieResponse
import com.example.moviecataloguejetpackpro.utils.DataDummy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    companion object {
        private const val TAG = "MovieViewModel"
        private const val API_KEY = BuildConfig.API_KEY
    }

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> = _movieList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    var movies: MutableLiveData<List<MovieEntity>>? = null
        get() {
            if (field == null) {
                field = MutableLiveData<List<MovieEntity>>()
                getMovies()
            }
            return field
        }
        private set

    fun getMovies(): List<MovieEntity> = DataDummy.generateDummyMovies()

    init {
        getMovieApi()
    }

    private fun getMovieApi() {
        _isLoading.value = true
        val client = ApiConfig.getApiService(API_KEY).getUpcomingMovies()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                movieResponse: Response<MovieResponse>,
            ) {
                _isLoading.value = false
                if (movieResponse.isSuccessful) {
                    _movieList.value = movieResponse.body()?.movies
                } else {
                    Log.e(TAG, "onResponse: ${movieResponse.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

    }

}