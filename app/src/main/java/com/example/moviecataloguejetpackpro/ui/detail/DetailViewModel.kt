package com.example.moviecataloguejetpackpro.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecataloguejetpackpro.data.Repository
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity

class DetailViewModel(private val repository: Repository) : ViewModel() {

    private var movieId: Int = 0
    private var tvShowId: Int = 0

    fun setMovieSelected(movieId: Int) {
        this.movieId = movieId
    }

    fun setTvShowSelected(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    fun getMovie(): LiveData<MovieEntity> = repository.getMovie(movieId)

    fun getTvShow(): LiveData<TVShowEntity> = repository.getTvShow(tvShowId)
}