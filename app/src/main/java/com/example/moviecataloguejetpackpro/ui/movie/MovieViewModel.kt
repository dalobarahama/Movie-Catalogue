package com.example.moviecataloguejetpackpro.ui.movie

import androidx.lifecycle.ViewModel
import com.example.moviecataloguejetpackpro.data.Entity
import com.example.moviecataloguejetpackpro.utils.DataDummy

class MovieViewModel : ViewModel() {

    fun getMovies(): List<Entity> = DataDummy.generateDummyMovies()

}