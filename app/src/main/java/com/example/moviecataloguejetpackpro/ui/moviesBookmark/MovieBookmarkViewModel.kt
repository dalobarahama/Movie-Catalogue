package com.example.moviecataloguejetpackpro.ui.moviesBookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecataloguejetpackpro.data.Repository
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntityLocal

class MovieBookmarkViewModel(private val repository: Repository) : ViewModel() {
    fun getBookmarkMovies(): LiveData<PagedList<MovieEntityLocal>> =
        repository.getBookmarkedMovies()
}
