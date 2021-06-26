package com.example.moviecataloguejetpackpro.ui.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecataloguejetpackpro.data.Repository
import com.example.moviecataloguejetpackpro.data.source.local.entity.TvShowEntityLocal
import com.example.moviecataloguejetpackpro.vo.Resource

class TVShowViewModel(private val repository: Repository) : ViewModel() {

    fun getAllTvShow(): LiveData<Resource<PagedList<TvShowEntityLocal>>> =
        repository.getAllTvShows()

}