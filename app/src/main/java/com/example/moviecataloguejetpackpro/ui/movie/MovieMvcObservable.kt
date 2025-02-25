package com.example.moviecataloguejetpackpro.ui.movie

import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.ui.common.basemvc.ViewMvcObservable

interface MovieMvcObservable : ViewMvcObservable<MovieMvcObservable.Listener> {
    interface Listener {
        fun onItemOnClicked(movieEntity: MovieEntity)
    }

    fun showData(movieList: List<MovieEntity>)
    fun showLoading()
    fun hideLoading()
    fun onFetchFailed()
}