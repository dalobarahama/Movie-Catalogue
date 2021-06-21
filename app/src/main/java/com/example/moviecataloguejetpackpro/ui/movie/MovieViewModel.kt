package com.example.moviecataloguejetpackpro.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecataloguejetpackpro.data.Repository
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity

class MovieViewModel(private val repository: Repository) : ViewModel() {

    fun getAllMovies(): LiveData<List<MovieEntity>> = repository.getAllMovies()

}