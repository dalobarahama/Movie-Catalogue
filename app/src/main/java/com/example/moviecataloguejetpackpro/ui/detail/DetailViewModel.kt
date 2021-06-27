package com.example.moviecataloguejetpackpro.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviecataloguejetpackpro.data.Repository
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntityLocal
import com.example.moviecataloguejetpackpro.data.source.local.entity.TvShowEntityLocal
import com.example.moviecataloguejetpackpro.vo.Resource

class DetailViewModel(private val repository: Repository) : ViewModel() {

    private var movieId = MutableLiveData<Int>()
    private var tvShowId = MutableLiveData<Int>()

    fun setMovieSelected(movieId: Int) {
        this.movieId.value = movieId
    }

    fun setTvShowSelected(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    var getMovie: LiveData<Resource<MovieEntityLocal>> =
        Transformations.switchMap(movieId) { movieId ->
            repository.getMovie(movieId)
        }

    var getTvShow: LiveData<Resource<TvShowEntityLocal>> =
        Transformations.switchMap(tvShowId) { tvShowId ->
            repository.getTvShow(tvShowId)
        }

    fun setMovieBookmark() {
        val movieResource = getMovie.value
        if (movieResource != null) {
            val movie = movieResource.data

            if (movie != null) {
                val newState = !movie.bookmarked
                repository.setMovieBookmark(movie, newState)
            }

        }
    }

    fun setTvShowBookmark() {
        val tvShowResource = getTvShow.value
        if (tvShowResource != null) {
            val tvShow = tvShowResource.data

            if (tvShow != null) {
                val newState = !tvShow.bookmarked
                repository.setTvShowBookmark(tvShow, newState)
            }
        }
    }
}