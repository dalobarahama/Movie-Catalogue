package com.example.moviecataloguejetpackpro.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.utils.EspressoIdlingResource
import com.example.moviecataloguejetpackpro.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getMovies(): LiveData<ApiResponse<List<MovieEntity>>> {
        EspressoIdlingResource.increment()
        val movieResults = MutableLiveData<ApiResponse<List<MovieEntity>>>()
        handler.postDelayed({
            movieResults.value = ApiResponse.success(jsonHelper.movieList)
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return movieResults
    }

    fun getTvShows(): LiveData<ApiResponse<List<TVShowEntity>>> {
        EspressoIdlingResource.increment()
        val tvShowResults = MutableLiveData<ApiResponse<List<TVShowEntity>>>()
        handler.postDelayed({
            tvShowResults.value = ApiResponse.success(jsonHelper.tvShowList)
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return tvShowResults
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieEntity: List<MovieEntity>)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShowEntity: List<TVShowEntity>)
    }

}