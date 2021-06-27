package com.example.moviecataloguejetpackpro.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
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

    fun getAllMovies(): DataSource.Factory<Int, MovieEntityLocal> = dao.getAllMovies()

    fun getAllTvShows(): DataSource.Factory<Int, TvShowEntityLocal> = dao.getAllTvShows()

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

    fun getBookmarkedMovies(): DataSource.Factory<Int, MovieEntityLocal> = dao.getBookmarkedMovies()

    fun getBookmarkedTvShows(): DataSource.Factory<Int, TvShowEntityLocal> =
        dao.getBookmarkedTvShows()
}