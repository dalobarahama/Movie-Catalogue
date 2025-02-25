package com.example.moviecataloguejetpackpro.ui.itemMovieTvShow

import com.example.moviecataloguejetpackpro.ui.common.basemvc.ViewMvcObservable

interface ItemMovieTvShowMvc<T : Any> : ViewMvcObservable<ItemMovieTvShowMvc.Listener<T>> {
    interface Listener<T : Any> {
        fun onItemClicked(entity: T)
    }

    fun bindData(entity: T)
}