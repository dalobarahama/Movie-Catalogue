package com.example.moviecataloguejetpackpro.data

import androidx.lifecycle.LiveData
import com.example.moviecataloguejetpackpro.data.source.DataSource
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.remote.RemoteDataSource

class FakeRepository(private val remoteDataSource: RemoteDataSource) : DataSource {
    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        TODO("Not yet implemented")
    }

    override fun getMovie(movieId: Int): LiveData<MovieEntity> {
        TODO("Not yet implemented")
    }

    override fun getAllTvShows(): LiveData<List<TVShowEntity>> {
        TODO("Not yet implemented")
    }

    override fun getTvShow(tvShowId: Int): LiveData<TVShowEntity> {
        TODO("Not yet implemented")
    }

}