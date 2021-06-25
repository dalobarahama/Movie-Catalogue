package com.example.moviecataloguejetpackpro.ui.tvShowsBookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecataloguejetpackpro.data.Repository
import com.example.moviecataloguejetpackpro.data.source.local.entity.TvShowEntityLocal

class TvShowBookmarkViewModel(private val repository: Repository) : ViewModel() {
    fun getBookmarkTvShows(): LiveData<List<TvShowEntityLocal>> = repository.getBookmarkedTvShows()
}