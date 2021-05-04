package com.example.moviecataloguejetpackpro.ui.tvShow

import androidx.lifecycle.ViewModel
import com.example.moviecataloguejetpackpro.data.TVShowEntity
import com.example.moviecataloguejetpackpro.utils.DataDummy

class TVShowViewModel : ViewModel() {

    fun getTVShow(): List<TVShowEntity> = DataDummy.generateDummyTVShows()

}