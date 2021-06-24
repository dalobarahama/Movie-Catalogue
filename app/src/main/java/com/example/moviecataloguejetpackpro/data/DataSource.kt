package com.example.moviecataloguejetpackpro.data

import androidx.lifecycle.LiveData
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.vo.Resource

interface DataSource {

    fun getAllMovies(): LiveData<Resource<List<MovieEntity>>>

    fun getMovie(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getAllTvShows(): LiveData<Resource<List<TVShowEntity>>>

    fun getTvShow(tvShowId: Int): LiveData<Resource<TVShowEntity>>

}