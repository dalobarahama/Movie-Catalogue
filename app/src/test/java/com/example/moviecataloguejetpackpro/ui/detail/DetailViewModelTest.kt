package com.example.moviecataloguejetpackpro.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecataloguejetpackpro.data.Repository
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id
    private val dummyTvShow = DataDummy.generateDummyTVShows()[0]
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var tvShowObserver: Observer<TVShowEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
        viewModel.setMovieSelected(movieId)
        viewModel.setTvShowSelected(tvShowId)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(repository.getMovie(movieId)).thenReturn(movie)
        val movieItem = viewModel.getMovie().value as MovieEntity
        assertNotNull(movieItem)
        assertEquals(dummyMovie.id, movieItem.id)
        assertEquals(dummyMovie.overview, movieItem.overview)
        assertEquals(dummyMovie.releaseDate, movieItem.releaseDate)
        assertEquals(dummyMovie.voteAverage, movieItem.voteAverage)
        assertEquals(dummyMovie.posterPath, movieItem.posterPath)

        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<TVShowEntity>()
        tvShow.value = dummyTvShow

        `when`(repository.getTvShow(tvShowId)).thenReturn(tvShow)
        val tvShowItem = viewModel.getTvShow().value as TVShowEntity
        assertNotNull(tvShowItem)
        assertEquals(dummyTvShow.id, tvShowItem.id)
        assertEquals(dummyTvShow.overview, tvShowItem.overview)
        assertEquals(dummyTvShow.firstAirDate, tvShowItem.firstAirDate)
        assertEquals(dummyTvShow.voteAverage, tvShowItem.voteAverage)
        assertEquals(dummyTvShow.posterPath, tvShowItem.posterPath)

        viewModel.getTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}