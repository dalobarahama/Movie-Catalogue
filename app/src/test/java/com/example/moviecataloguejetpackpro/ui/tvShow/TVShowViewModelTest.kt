package com.example.moviecataloguejetpackpro.ui.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecataloguejetpackpro.data.Repository
import com.example.moviecataloguejetpackpro.data.source.local.entity.TvShowEntityLocal
import com.example.moviecataloguejetpackpro.utils.DataDummy
import com.example.moviecataloguejetpackpro.vo.Resource
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
class TVShowViewModelTest {

    private lateinit var viewModel: TVShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<Resource<List<TvShowEntityLocal>>>

    @Before
    fun setUp() {
        viewModel = TVShowViewModel(repository)
    }

    @Test
    fun getTVShow() {
        val dummyData = Resource.success(DataDummy.generateDummyTVShows())
        val tvShows = MutableLiveData<Resource<List<TvShowEntityLocal>>>()
        tvShows.value = dummyData

        `when`(repository.getAllTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getAllTvShow().value?.data
        Mockito.verify(repository).getAllTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(3, tvShowEntities?.size)

        viewModel.getAllTvShow().observeForever(observer)
        verify(observer).onChanged(dummyData)
    }
}