package com.example.moviecataloguejetpackpro.ui.tvShow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecataloguejetpackpro.data.Entity
import com.example.moviecataloguejetpackpro.utils.DataDummy

class TVShowViewModel : ViewModel() {

    var tvShows: MutableLiveData<List<Entity>>? = null
        get() {
            if (field == null) {
                field = MutableLiveData<List<Entity>>()
                getTVShow()
            }
            return field
        }
        private set

    fun getTVShow(): List<Entity> = DataDummy.generateDummyTVShows()

}