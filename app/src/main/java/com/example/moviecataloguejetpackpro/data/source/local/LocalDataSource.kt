package com.example.moviecataloguejetpackpro.data.source.local

import androidx.lifecycle.LiveData
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntityLocal
import com.example.moviecataloguejetpackpro.data.source.local.entity.TvShowEntityLocal
import com.example.moviecataloguejetpackpro.data.source.local.room.Dao

class LocalDataSource private constructor(private val dao: Dao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(dao: Dao): LocalDataSource =
            INSTANCE ?: LocalDataSource(dao).apply {
                INSTANCE = this
            }
    }

    fun getAllMovies(): LiveData<List<MovieEntityLocal>> = dao.getAllMovies()

    fun getAllTvShows(): LiveData<List<TvShowEntityLocal>> = dao.getAllTvShows()

    fun getMovieById(movieId: Int): LiveData<MovieEntityLocal> = dao.getMovieById(movieId)

    fun getTvShowById(tvShowId: Int): LiveData<TvShowEntityLocal> = dao.getTvShowId(tvShowId)

    fun insertMovies(movies: List<MovieEntityLocal>) = dao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvShowEntityLocal>) = dao.insertTvShows(tvShows)

    fun setMovieBookmark(movie: MovieEntityLocal, newState: Boolean) {
        movie.bookmarked = newState
        dao.updateMovie(movie)
    }

    fun setTvShowBookmark(tvShow: TvShowEntityLocal, newState: Boolean) {
        tvShow.bookmarked = newState
        dao.updateTvShow(tvShow)
    }

    fun getBookmarkedMovies(): LiveData<List<MovieEntityLocal>> = dao.getBookmarkedMovies()

    fun getBookmarkedTvShows(): LiveData<List<TvShowEntityLocal>> = dao.getBookmarkedTvShows()
}