package com.example.moviecataloguejetpackpro.data.source

import com.example.moviecataloguejetpackpro.data.Entity
import com.example.moviecataloguejetpackpro.data.source.remote.RemoteDataSource

class Repository private constructor(private val remoteDataSource: RemoteDataSource) :
    EntityDataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteDataSource).apply { instance = this }
            }
    }

    override fun getAllMovies(): List<Entity> {
        val movieResponse = remoteDataSource.getAllMovies()
        val movieList = ArrayList<Entity>()
        for (response in movieResponse) {
            val movie = Entity(
                response.type,
                response.id,
                response.title,
                response.overview,
                response.tags,
                response.score,
                response.releaseDate,
                response.imagePath
            )

            movieList.add(movie)
        }
        return movieList
    }

    override fun getAllTVShow(): List<Entity> {
        val tvShowResponse = remoteDataSource.getAllTVShows()
        val tvShowList = ArrayList<Entity>()
        for (response in tvShowResponse) {
            val tvShow = Entity(
                response.type,
                response.id,
                response.title,
                response.overview,
                response.tags,
                response.score,
                response.releaseDate,
                response.imagePath
            )

            tvShowList.add(tvShow)
        }
        return tvShowList
    }
}