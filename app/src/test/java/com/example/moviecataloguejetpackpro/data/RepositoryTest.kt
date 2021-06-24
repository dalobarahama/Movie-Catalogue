package com.example.moviecataloguejetpackpro.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.remote.RemoteDataSource
import com.example.moviecataloguejetpackpro.utils.DataDummy
import com.example.moviecataloguejetpackpro.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val repository = FakeRepository(remote)

    private val movies: List<MovieEntity> = DataDummy.generateDummyMovies()
    private val movieId = movies[0].id
    private val tvShows: List<TVShowEntity> = DataDummy.generateDummyTVShows()
    private val tvShowId = tvShows[0].id


    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onAllMoviesReceived(
                movies)
            null
        }.`when`(remote).getMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(repository.getAllMovies())
        verify(remote).getMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movies.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getAllTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback).onAllTvShowReceived(
                tvShows)
            null
        }.`when`(remote).getTvShows(any())
        val tvShowEntities = LiveDataTestUtil.getValue(repository.getAllTvShows())
        verify(remote).getTvShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShows.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onAllMoviesReceived(
                movies)
            null
        }.`when`(remote).getMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(repository.getMovie(movieId))
        verify(remote).getMovies(any())
        assertNotNull(movieEntities)
        assertNotNull(movieEntities.title)
        assertEquals(movies[0].title, movieEntities.title)
    }

    @Test
    fun getTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback).onAllTvShowReceived(
                tvShows)
            null
        }.`when`(remote).getTvShows(any())
        val tvShowEntities = LiveDataTestUtil.getValue(repository.getTvShow(tvShowId))
        verify(remote).getTvShows(any())
        assertNotNull(tvShowEntities)
        assertNotNull(tvShowEntities.name)
        assertEquals(tvShows[0].name, tvShowEntities.name)
    }
}