package com.example.moviecataloguejetpackpro.ui.tvShow

import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.ui.common.basemvc.ViewMvcObservable

interface TVShowsMvc : ViewMvcObservable<TVShowsMvc.Listener> {
    interface Listener {
        fun onItemOnClicked(tvShowEntity: TVShowEntity)
    }

    fun showData(tvShowList: List<TVShowEntity>)
    fun onFetchFailed()
    fun showLoading()
    fun hideLoading()
}