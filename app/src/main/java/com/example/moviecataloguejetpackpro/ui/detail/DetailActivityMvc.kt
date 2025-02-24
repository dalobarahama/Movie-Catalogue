package com.example.moviecataloguejetpackpro.ui.detail

import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.moviecataloguejetpackpro.data.source.local.entity.DetailEntity

interface DetailActivityMvc {
    fun getRootView(): View
    fun registerListener(listener: DetailActivityMvcImpl.Listener)
    fun unregisterListener(listener: DetailActivityMvcImpl.Listener)
    fun populateTrendingEntity(detailEntity: DetailEntity)
    fun setBookmarkState(state: Boolean)
    fun getToolbar(): Toolbar
}