package com.example.moviecataloguejetpackpro.ui.tvShowsBookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecataloguejetpackpro.data.Repository
import com.example.moviecataloguejetpackpro.data.source.local.entity.TvShowEntityLocal
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
class TvShowBookmarkViewModelTest {
    private lateinit var viewModel: TvShowBookmarkViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntityLocal>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntityLocal>

    @Before
    fun setUp() {
        viewModel = TvShowBookmarkViewModel(repository)
    }

    @Test
    fun getBookmarkTvShows() {
        val dummyTvShows = pagedList
        `when`(dummyTvShows.size).thenReturn(3)
        val tvShows = MutableLiveData<PagedList<TvShowEntityLocal>>()
        tvShows.value = dummyTvShows

        `when`(repository.getBookmarkedTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getBookmarkTvShows().value
        Mockito.verify(repository).getBookmarkedTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(3, tvShowEntities?.size)

        viewModel.getBookmarkTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}