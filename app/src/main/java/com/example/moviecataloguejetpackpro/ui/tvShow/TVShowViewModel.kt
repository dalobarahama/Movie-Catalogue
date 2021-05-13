package com.example.moviecataloguejetpackpro.ui.tvShow

import androidx.lifecycle.ViewModel
import com.example.moviecataloguejetpackpro.data.Entity
import com.example.moviecataloguejetpackpro.data.source.Repository

class TVShowViewModel(private val repository: Repository) : ViewModel() {

    fun getTVShow(): List<Entity> = repository.getAllTVShow()

}