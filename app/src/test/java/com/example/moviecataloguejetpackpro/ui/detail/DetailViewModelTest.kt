package com.example.moviecataloguejetpackpro.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecataloguejetpackpro.data.Repository
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntityLocal
import com.example.moviecataloguejetpackpro.data.source.local.entity.TvShowEntityLocal
import com.example.moviecataloguejetpackpro.utils.DataDummy
import com.example.moviecataloguejetpackpro.vo.Resource
import com.nhaarman.mockitokotlin2.verify
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
    private lateinit var movieObserver: Observer<Resource<MovieEntityLocal>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntityLocal>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
        viewModel.setMovieSelected(movieId)
        viewModel.setTvShowSelected(tvShowId)
    }

    @Test
    fun getMovie() {
        val dummyMovieById = Resource.success(DataDummy.generateDummyMovie(dummyMovie, true))
        val movie = MutableLiveData<Resource<MovieEntityLocal>>()
        movie.value = dummyMovieById

        `when`(repository.getMovie(movieId)).thenReturn(movie)

        viewModel.getMovie.observeForever(movieObserver)

        verify(movieObserver).onChanged(dummyMovieById)
    }

    @Test
    fun getTvShow() {
        val dummyTvShowId = Resource.success(DataDummy.generateDummyTvShow(dummyTvShow, true))
        val tvShow = MutableLiveData<Resource<TvShowEntityLocal>>()
        tvShow.value = dummyTvShowId

        `when`(repository.getTvShow(tvShowId)).thenReturn(tvShow)

        viewModel.getTvShow.observeForever(tvShowObserver)

        verify(tvShowObserver).onChanged(dummyTvShowId)
    }
}