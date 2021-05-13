package com.example.moviecataloguejetpackpro.ui.movie

import androidx.lifecycle.ViewModel
import com.example.moviecataloguejetpackpro.data.Entity
import com.example.moviecataloguejetpackpro.data.source.Repository

class MovieViewModel(private val repository: Repository) : ViewModel() {

    fun getMovies(): List<Entity> = repository.getAllMovies()

}