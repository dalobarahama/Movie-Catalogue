package com.example.moviecataloguejetpackpro.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntityLocal
import com.example.moviecataloguejetpackpro.data.source.local.entity.TvShowEntityLocal

@Dao
interface Dao {

    @Query("SELECT * FROM movie_entities")
    fun getAllMovies(): LiveData<List<MovieEntityLocal>>

    @Query("SELECT * FROM movie_entities WHERE id = :movieId")
    fun getMovieById(movieId: Int): LiveData<MovieEntityLocal>

    @Query("SELECT * FROM tv_show_entities")
    fun getAllTvShows(): LiveData<List<TvShowEntityLocal>>

    @Query("SELECT * FROM tv_show_entities WHERE id = :tvShowId")
    fun getTvShowId(tvShowId: Int): LiveData<TvShowEntityLocal>

    @Query("SELECT * FROM movie_entities WHERE bookmarked = 1")
    fun getBookmarkedMovies(): LiveData<List<MovieEntityLocal>>

    @Query("SELECT * FROM tv_show_entities WHERE bookmarked = 1")
    fun getBookmarkedTvShows(): LiveData<List<TvShowEntityLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntityLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntityLocal>)

    @Update
    fun updateMovie(movie: MovieEntityLocal)

    @Update
    fun updateTvShow(tvShow: TvShowEntityLocal)
}