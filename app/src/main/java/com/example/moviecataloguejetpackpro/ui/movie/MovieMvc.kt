package com.example.moviecataloguejetpackpro.ui.movie

import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.ui.common.basemvc.BaseObservableMvc

interface MovieMvc: BaseObservableMvc<MovieMvc.Listener> {
    interface Listener {

    }

    fun showData(movieList: List<MovieEntity>)
    fun showLoading()
    fun hideLoading()
    fun onFetchFailed()
}