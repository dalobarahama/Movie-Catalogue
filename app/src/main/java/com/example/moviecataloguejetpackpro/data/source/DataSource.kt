package com.example.moviecataloguejetpackpro.data.source

import androidx.lifecycle.LiveData
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity

interface DataSource {

    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun getMovie(movieId: Int): LiveData<MovieEntity>

    fun getAllTvShows(): LiveData<List<TVShowEntity>>

    fun getTvShow(tvShowId: Int): LiveData<TVShowEntity>

}