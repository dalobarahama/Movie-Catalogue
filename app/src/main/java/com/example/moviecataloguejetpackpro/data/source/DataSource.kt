package com.example.moviecataloguejetpackpro.data.source

import androidx.lifecycle.LiveData
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity

interface DataSource {

    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun getAllTvShows(): LiveData<List<TVShowEntity>>

    fun getMovieByPosition(position: Int): MovieEntity?

    fun getTvShowByPosition(position: Int): TVShowEntity?

}