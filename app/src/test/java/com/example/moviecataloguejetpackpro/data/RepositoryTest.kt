package com.example.moviecataloguejetpackpro.data

import androidx.lifecycle.LiveData
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.remote.RemoteDataSource
import com.example.moviecataloguejetpackpro.utils.DataDummy
import org.junit.Test
import org.mockito.Mockito

class RepositoryTest {
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val repository = FakeRepository(remote)

    private val movies: LiveData<List<MovieEntity>> = DataDummy.generateDummyLiveDataMovies()
    private val movieId = movies.value!![0].id
    private val tvShows: LiveData<List<TVShowEntity>> = DataDummy.generateDummyLiveDataTVShows()
    private val tvShowId = tvShows.value!![0].id


    @Test
    fun getAllMovies() {
    }
}