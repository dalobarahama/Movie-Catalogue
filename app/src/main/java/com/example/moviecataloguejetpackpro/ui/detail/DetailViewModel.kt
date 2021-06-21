package com.example.moviecataloguejetpackpro.ui.detail

import androidx.lifecycle.ViewModel
import com.example.moviecataloguejetpackpro.data.Repository
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity

class DetailViewModel(private val repository: Repository) : ViewModel() {

    fun getMovieByPosition(position: Int): MovieEntity? = repository.getMovieByPosition(position)

    fun getTvShowByPosition(position: Int): TVShowEntity? = repository.getTvShowByPosition(position)

}