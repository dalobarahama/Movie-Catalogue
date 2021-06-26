package com.example.moviecataloguejetpackpro.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecataloguejetpackpro.data.source.local.LocalDataSource
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntityLocal
import com.example.moviecataloguejetpackpro.data.source.local.entity.TvShowEntityLocal
import com.example.moviecataloguejetpackpro.data.source.remote.RemoteDataSource
import com.example.moviecataloguejetpackpro.utils.AppExecutors
import com.example.moviecataloguejetpackpro.utils.DataDummy
import com.example.moviecataloguejetpackpro.utils.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val repository = FakeRepository(remote, local, appExecutors)

    private val movies = DataDummy.generateDummyMovies()
    private val movieId = movies[0].id
    private val tvShows = DataDummy.generateDummyTVShows()
    private val tvShowId = tvShows[0].id


    @Test
    fun getAllMovies() {
        val dummyMovies = MutableLiveData<List<MovieEntityLocal>>()
        dummyMovies.value = DataDummy.generateDummyMovies()
        `when`(local.getAllMovies()).thenReturn(dummyMovies)

        val movieEntities = LiveDataTestUtil.getValue(repository.getAllMovies())
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movies.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShows() {
        val dummyTvShows = MutableLiveData<List<TvShowEntityLocal>>()
        dummyTvShows.value = DataDummy.generateDummyTVShows()
        `when`(local.getAllTvShows()).thenReturn(dummyTvShows)

        val tvShowEntities = LiveDataTestUtil.getValue(repository.getAllTvShows())
        verify(local).getAllTvShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShows.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getMovie() {
        val dummyMovie = MutableLiveData<MovieEntityLocal>()
        dummyMovie.value = DataDummy.generateDummyMovie(DataDummy.generateDummyMovies()[0], false)
        `when`(local.getMovieById(movieId)).thenReturn(dummyMovie)

        val movieEntities = LiveDataTestUtil.getValue(repository.getMovie(movieId))
        verify(local).getMovieById(movieId)
        assertNotNull(movieEntities.data)
        assertNotNull(movieEntities.data?.title)
        assertEquals(movies[0].title, movieEntities.data?.title)
    }

    @Test
    fun getTvShow() {
        val dummyTvShow = MutableLiveData<TvShowEntityLocal>()
        dummyTvShow.value =
            DataDummy.generateDummyTvShow(DataDummy.generateDummyTVShows()[0], false)
        `when`(local.getTvShowById(tvShowId)).thenReturn(dummyTvShow)

        val tvShowEntities = LiveDataTestUtil.getValue(repository.getTvShow(tvShowId))
        verify(local).getTvShowById(tvShowId)
        assertNotNull(tvShowEntities.data)
        assertNotNull(tvShowEntities.data?.name)
        assertEquals(tvShows[0].name, tvShowEntities.data?.name)
    }

    @Test
    fun getBookmarkedMovies() {
        val dummyMovies = MutableLiveData<List<MovieEntityLocal>>()
        dummyMovies.value = DataDummy.generateDummyMovies()
        `when`(local.getBookmarkedMovies()).thenReturn(dummyMovies)

        val movieEntities = LiveDataTestUtil.getValue(repository.getBookmarkedMovies())
        verify(local).getBookmarkedMovies()
        assertNotNull(movieEntities)
        assertEquals(movies.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getBookmarkedTvShows() {
        val dummyTvShows = MutableLiveData<List<TvShowEntityLocal>>()
        dummyTvShows.value = DataDummy.generateDummyTVShows()
        `when`(local.getBookmarkedTvShows()).thenReturn(dummyTvShows)

        val tvShowEntities = LiveDataTestUtil.getValue(repository.getBookmarkedTvShows())
        verify(local).getBookmarkedTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShows.size.toLong(), tvShowEntities.size.toLong())
    }
}