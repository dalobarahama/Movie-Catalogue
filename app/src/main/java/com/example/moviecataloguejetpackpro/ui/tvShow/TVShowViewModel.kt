package com.example.moviecataloguejetpackpro.ui.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecataloguejetpackpro.data.source.Repository
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity

class TVShowViewModel(private val repository: Repository) : ViewModel() {

    fun getAllTvShow(): LiveData<List<TVShowEntity>> = repository.getAllTvShows()

}