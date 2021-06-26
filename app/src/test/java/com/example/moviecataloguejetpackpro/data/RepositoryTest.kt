package com.example.moviecataloguejetpackpro.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecataloguejetpackpro.data.source.local.LocalDataSource
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntityLocal
import com.example.moviecataloguejetpackpro.data.source.local.entity.TvShowEntityLocal
import com.example.moviecataloguejetpackpro.data.source.remote.RemoteDataSource
import com.example.moviecataloguejetpackpro.utils.AppExecutors
import com.example.moviecataloguejetpackpro.utils.DataDummy
import com.example.moviecataloguejetpackpro.utils.LiveDataTestUtil
import com.example.moviecataloguejetpackpro.utils.PagedListUtil
import com.example.moviecataloguejetpackpro.vo.Resource
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
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntityLocal>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        repository.getAllMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movies.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntityLocal>
        `when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        repository.getAllTvShows()

        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTVShows()))
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
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntityLocal>
        `when`(local.getBookmarkedMovies()).thenReturn(dataSourceFactory)
        repository.getBookmarkedMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getBookmarkedMovies()
        assertNotNull(movieEntities)
        assertEquals(movies.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getBookmarkedTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntityLocal>
        `when`(local.getBookmarkedTvShows()).thenReturn(dataSourceFactory)
        repository.getBookmarkedTvShows()

        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTVShows()))
        verify(local).getBookmarkedTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShows.size.toLong(), tvShowEntities.data?.size?.toLong())
    }
}