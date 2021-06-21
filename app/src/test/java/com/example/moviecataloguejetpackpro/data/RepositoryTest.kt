package com.example.moviecataloguejetpackpro.data

import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.remote.RemoteDataSource
import com.example.moviecataloguejetpackpro.utils.DataDummy
import org.junit.Test
import org.mockito.Mockito

class RepositoryTest {
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val repository = FakeRepository(remote)

    private val movies: List<MovieEntity> = DataDummy.generateDummyMovies()
    private val movieId = movies[0].id
    private val tvShows: List<TVShowEntity> = DataDummy.generateDummyTVShows()
    private val tvShowId = tvShows[0].id


    @Test
    fun getAllMovies() {
    }
}