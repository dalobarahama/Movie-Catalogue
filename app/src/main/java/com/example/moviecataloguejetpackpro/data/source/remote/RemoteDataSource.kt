package com.example.moviecataloguejetpackpro.data.source.remote

import android.os.Handler
import android.os.Looper
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

    fun getMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllMoviesReceived(jsonHelper.movieList)
            EspressoIdlingResource.decrement()
        },
            SERVICE_LATENCY_IN_MILLIS)
    }

    fun getTvShows(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllTvShowReceived(jsonHelper.tvShowList)
            EspressoIdlingResource.decrement()
        },
            SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieEntity: List<MovieEntity>)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShowEntity: List<TVShowEntity>)
    }

}