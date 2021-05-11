package com.example.moviecataloguejetpackpro.data.source.remote

import com.example.moviecataloguejetpackpro.data.source.remote.response.EntityResponse
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

    fun getAllMovies(): List<EntityResponse> = jsonHelper.loadMovies()

    fun getAllTVShows(): List<EntityResponse> = jsonHelper.loadTVShows()
}