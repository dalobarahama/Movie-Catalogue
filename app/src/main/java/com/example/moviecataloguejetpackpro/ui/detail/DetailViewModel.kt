package com.example.moviecataloguejetpackpro.ui.detail

import androidx.lifecycle.ViewModel
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.utils.DataDummy

class DetailViewModel : ViewModel() {
    private lateinit var type: String
    private lateinit var id: String

    fun setSelectedItem(type: String, id: String) {
        this.type = type
        this.id = id
    }

    fun getItem(): MovieEntity {
        lateinit var movieEntity: MovieEntity
        val movieList = DataDummy.generateDummyMovies()
        val tvShowList = DataDummy.generateDummyTVShows()

        if (type.equals("movie", true)) {
//            for (movieEntity in movieList) {
//                if (movieEntity.id == id) {
//                    movieEntity = movieEntity
//                }
//            }
        } else {
            for (tvShowEntity in tvShowList) {
                if (tvShowEntity.id == id) {
                    movieEntity = tvShowEntity
                }
            }
        }

        return movieEntity
    }

}