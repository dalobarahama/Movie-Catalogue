package com.example.moviecataloguejetpackpro.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecataloguejetpackpro.data.Entity
import com.example.moviecataloguejetpackpro.utils.DataDummy

class MovieViewModel : ViewModel() {

    var movies: MutableLiveData<List<Entity>>? = null
        get() {
            if (field == null) {
                field = MutableLiveData<List<Entity>>()
                getMovies()
            }
            return field
        }
        private set

    fun getMovies(): List<Entity> = DataDummy.generateDummyMovies()

}