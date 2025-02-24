package com.example.moviecataloguejetpackpro.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviecataloguejetpackpro.data.source.remote.response.Result
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.FetchMovieUseCase
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.FetchNowPlayingUseCase
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.FetchTrendingUseCase
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.FetchTvPopularUseCase
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.FetchTvShowUseCase
import com.example.moviecataloguejetpackpro.ui.common.BaseFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeFragmentMvcImpl.Listener {
    @Inject
    lateinit var fetchTrendingUseCase: FetchTrendingUseCase

    @Inject
    lateinit var fetchMovieUseCase: FetchMovieUseCase

    @Inject
    lateinit var fetchTvShowUseCase: FetchTvShowUseCase

    @Inject
    lateinit var fetchNowPlayingUseCase: FetchNowPlayingUseCase

    @Inject
    lateinit var fetchTvPopularUseCase: FetchTvPopularUseCase

    private lateinit var viewMvc: HomeFragmentMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        viewMvc = HomeFragmentMvcImpl(layoutInflater, null)

        fetchTrendingFromApi()
        fetchNowPlayingFromApi()
        fetchTvPopularFromApi()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return viewMvc.getRootView()
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
    }

    override fun onStop() {
        viewMvc.unregisterListener(this)
        super.onStop()
    }

    private fun fetchTrendingFromApi() {
        coroutineScope.launch {
            viewMvc.showShimmerLayoutTrending()
            try {
                when (val result = fetchTrendingUseCase.fetchTrending()) {
                    is FetchTrendingUseCase.Result.Success -> viewMvc.bindTrendingData(result.trendingList)

                    is FetchTrendingUseCase.Result.Failure -> viewMvc.onFetchFailed()
                }
            } finally {
                viewMvc.hideShimmerLayoutTrending()
            }
        }
    }

    private fun fetchNowPlayingFromApi() {
        coroutineScope.launch {
            viewMvc.showShimmerLayoutNewMovies()
            try {
                when (val result = fetchNowPlayingUseCase.fetchNowPlaying()) {
                    is Result.Success -> viewMvc.bindNowPlayingData(result.responseList)

                    is Result.Failure -> viewMvc.onFetchFailed()
                }
            } finally {
                viewMvc.hideShimmerLayoutNewMovies()
            }
        }
    }

    private fun fetchTvPopularFromApi() {
        coroutineScope.launch {
            viewMvc.showShimmerLayoutTvSeries()
            try {
                when (val result = fetchTvPopularUseCase.fetchTvPopular()) {
                    is Result.Success -> viewMvc.bindTvPopularData(result.responseList)

                    is Result.Failure -> viewMvc.onFetchFailed()
                }
            } finally {
                viewMvc.hideShimmerLayoutTvSeries()
            }
        }
    }

    override fun onItemClicked() {

    }
}