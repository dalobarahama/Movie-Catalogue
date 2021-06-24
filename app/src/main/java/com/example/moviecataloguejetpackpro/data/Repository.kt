package com.example.moviecataloguejetpackpro.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.remote.RemoteDataSource

class Repository private constructor(private val remoteDataSource: RemoteDataSource) : DataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteDataSource).apply { instance = this }
            }
    }

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        Log.i("Repository", "getAllMovies")
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieEntity: List<MovieEntity>) {
                Log.i("Repository", "onAllMoviesReceived: " + movieEntity[0].title)
                val movieList = ArrayList<MovieEntity>()
                for (response in movieEntity) {
                    Log.i("Repository", "onAllMoviesReceived: " + response.title)
                    movieList.add(response)
                }
                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }

    override fun getAllTvShows(): LiveData<List<TVShowEntity>> {
        Log.i("Repository", "getAllTvShow")
        val tvShowResults = MutableLiveData<List<TVShowEntity>>()
        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowEntity: List<TVShowEntity>) {
                Log.i("Repository", "onAllTvShowReceived: " + tvShowEntity[0].name)
                val tvShowList = ArrayList<TVShowEntity>()
                for (response in tvShowEntity) {
                    Log.i("Repository", "onAllMoviesReceived: " + response.name)
                    tvShowList.add(response)
                }
                tvShowResults.postValue(tvShowList)
            }
        })
        return tvShowResults
    }

    override fun getMovie(movieId: Int): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()

        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieEntity: List<MovieEntity>) {
                lateinit var movie: MovieEntity
                for (response in movieEntity) {
                    if (response.id == movieId) {
                        movie = response
                    }
                }
                movieResult.postValue(movie)
            }
        })

        return movieResult
    }

    override fun getTvShow(tvShowId: Int): LiveData<TVShowEntity> {
        val tvShowResult = MutableLiveData<TVShowEntity>()

        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowEntity: List<TVShowEntity>) {
                lateinit var tvShow: TVShowEntity
                for (response in tvShowEntity) {
                    if (response.id == tvShowId) {
                        tvShow = response
                    }
                }
                tvShowResult.postValue(tvShow)
            }
        })

        return tvShowResult
    }
}