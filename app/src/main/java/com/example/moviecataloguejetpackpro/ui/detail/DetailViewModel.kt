package com.example.moviecataloguejetpackpro.ui.detail

import androidx.lifecycle.ViewModel
import com.example.moviecataloguejetpackpro.data.Entity
import com.example.moviecataloguejetpackpro.utils.DataDummy

class DetailViewModel : ViewModel() {
    private lateinit var type: String
    private lateinit var id: String

    fun setSelectedItem(type: String, id: String) {
        this.type = type
        this.id = id
    }

    fun getItem(): Entity {
        lateinit var entity: Entity
        val movieList = DataDummy.generateDummyMovies()
        val tvShowList = DataDummy.generateDummyTVShows()

        if (type.equals("movie", true)) {
            for (movieEntity in movieList) {
                if (movieEntity.id == id) {
                    entity = movieEntity
                }
            }
        } else {
            for (tvShowEntity in tvShowList) {
                if (tvShowEntity.id == id) {
                    entity = tvShowEntity
                }
            }
        }

        return entity
    }

}