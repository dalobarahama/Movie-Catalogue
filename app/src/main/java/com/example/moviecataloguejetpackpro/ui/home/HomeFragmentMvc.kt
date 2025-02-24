package com.example.moviecataloguejetpackpro.ui.home

import android.view.View
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TrendingEntity

interface HomeFragmentMvc {
    fun getRootView(): View
    fun registerListener(listener: HomeFragmentMvcImpl.Listener)
    fun unregisterListener(listener: HomeFragmentMvcImpl.Listener)
    fun onFetchFailed()
    fun bindTrendingData(trendingList: List<TrendingEntity>)
    fun bindNowPlayingData(movieList: List<MovieEntity>)
    fun bindTvPopularData(tvShowList: List<TVShowEntity>)
    fun showShimmerLayoutTrending()
    fun hideShimmerLayoutTrending()
    fun showShimmerLayoutNewMovies()
    fun hideShimmerLayoutNewMovies()
    fun showShimmerLayoutTvSeries()
    fun hideShimmerLayoutTvSeries()
}