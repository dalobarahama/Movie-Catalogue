package com.example.moviecataloguejetpackpro.ui.movie

import androidx.lifecycle.ViewModel
import com.example.moviecataloguejetpackpro.data.MovieEntity
import com.example.moviecataloguejetpackpro.utils.DataDummy

class MovieViewModel : ViewModel() {

    fun getMovies(): List<MovieEntity> = DataDummy.generateDummyMovies()

}