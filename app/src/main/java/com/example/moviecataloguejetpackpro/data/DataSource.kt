package com.example.moviecataloguejetpackpro.data

import androidx.lifecycle.LiveData
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntityLocal
import com.example.moviecataloguejetpackpro.data.source.local.entity.TvShowEntityLocal
import com.example.moviecataloguejetpackpro.vo.Resource

interface DataSource {

    fun getAllMovies(): LiveData<Resource<List<MovieEntityLocal>>>

    fun getMovie(movieId: Int): LiveData<Resource<MovieEntityLocal>>

    fun getAllTvShows(): LiveData<Resource<List<TvShowEntityLocal>>>

    fun getTvShow(tvShowId: Int): LiveData<Resource<TvShowEntityLocal>>

    fun getBookmarkedMovies(): LiveData<List<MovieEntityLocal>>

    fun getBookmarkedTvShows(): LiveData<List<TvShowEntityLocal>>

    fun setMovieBookmark(movie: MovieEntityLocal, state: Boolean)

    fun setTvShowBookmark(tvShow: TvShowEntityLocal, state: Boolean)

}