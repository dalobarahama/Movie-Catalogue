package com.example.moviecataloguejetpackpro.data

import androidx.lifecycle.LiveData
import com.example.moviecataloguejetpackpro.data.source.DataSource
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.remote.RemoteDataSource

class FakeRepository(private val remoteDataSource: RemoteDataSource) : DataSource {

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        return remoteDataSource.getMovies()
    }

    override fun getAllTvShows(): LiveData<List<TVShowEntity>> {
        return remoteDataSource.getTvShows()
    }

    override fun getMovieByPosition(position: Int): MovieEntity? {
        return remoteDataSource.getMovieByPosition(position)
    }

    override fun getTvShowByPosition(position: Int): TVShowEntity? {
        return remoteDataSource.getTvShowByPosition(position)
    }
}