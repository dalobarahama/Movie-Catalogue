package com.example.moviecataloguejetpackpro.ui.moviesBookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecataloguejetpackpro.data.Repository
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntityLocal
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieBookmarkViewModelTest {

    private lateinit var viewModel: MovieBookmarkViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntityLocal>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntityLocal>

    @Before
    fun setUp() {
        viewModel = MovieBookmarkViewModel(repository)
    }

    @Test
    fun getBookmarkMovies() {
        val dummyMovies = pagedList
        `when`(dummyMovies.size).thenReturn(3)
        val movies = MutableLiveData<PagedList<MovieEntityLocal>>()
        movies.value = dummyMovies

        `when`(repository.getBookmarkedMovies()).thenReturn(movies)
        val tvShowEntities = viewModel.getBookmarkMovies().value
        Mockito.verify(repository).getBookmarkedMovies()
        assertNotNull(tvShowEntities)
        assertEquals(3, tvShowEntities?.size)

        viewModel.getBookmarkMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}