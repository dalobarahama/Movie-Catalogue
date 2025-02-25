package com.example.moviecataloguejetpackpro.ui.itemMovieTvShow

import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.ui.common.basemvc.ViewMvcObservable

interface ItemMovieTvShowMvc: ViewMvcObservable<ItemMovieTvShowMvc.Listener> {
    interface Listener {
        fun onItemClicked(movieEntity: MovieEntity)
    }

    fun bindData(movieEntity: MovieEntity)
}