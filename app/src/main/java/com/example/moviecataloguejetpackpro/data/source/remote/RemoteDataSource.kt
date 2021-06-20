package com.example.moviecataloguejetpackpro.data.source.remote

import androidx.lifecycle.LiveData
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getMovies(): LiveData<List<MovieEntity>> = jsonHelper.movieList

    fun getTvShows(): LiveData<List<TVShowEntity>> = jsonHelper.tvShowList
}