package com.example.moviecataloguejetpackpro.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TrendingEntity
import com.example.moviecataloguejetpackpro.data.source.remote.response.Result
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.*
import com.example.moviecataloguejetpackpro.databinding.FragmentHomeBinding
import com.example.moviecataloguejetpackpro.ui.common.BaseFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : BaseFragment() {
    private var _fragmentBinding: FragmentHomeBinding? = null
    private val binding get() = _fragmentBinding

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

    override fun onStart() {
        super.onStart()
        fetchTrendingFromApi()
        fetchNowPlayingFromApi()
        fetchTvPopularFromApi()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _fragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun fetchTrendingFromApi() {
        coroutineScope.launch {
            binding?.trendingProgressBar?.visibility = View.VISIBLE
            try {
                when (val result = fetchTrendingUseCase.fetchTrending()) {
                    is FetchTrendingUseCase.Result.Success -> bindTrendingData(result.trendingList)

                    is FetchTrendingUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                binding?.trendingProgressBar?.visibility = View.GONE
            }
        }
    }

    private fun fetchNowPlayingFromApi() {
        coroutineScope.launch {
            binding?.newMoviesProgressBar?.visibility = View.VISIBLE
            try {
                when (val result = fetchNowPlayingUseCase.fetchNowPlaying()) {
                    is Result.Success -> bindNowPlayingData(result.responseList)

                    is Result.Failure -> onFetchFailed()
                }
            } finally {
                binding?.newMoviesProgressBar?.visibility = View.GONE
            }
        }
    }

    private fun fetchTvPopularFromApi() {
        coroutineScope.launch {
            binding?.tvSeriesProgressBar?.visibility = View.VISIBLE
            try {
                when (val result = fetchTvPopularUseCase.fetchTvPopular()) {
                    is Result.Success -> bindTvPopularData(result.responseList)

                    is Result.Failure -> onFetchFailed()
                }
            } finally {
                binding?.tvSeriesProgressBar?.visibility = View.GONE
            }
        }
    }


    private fun onFetchFailed() {
        Toast.makeText(requireContext(), "Fetch Failed", Toast.LENGTH_SHORT).show()
    }


    private fun bindTrendingData(trendingList: List<TrendingEntity>) {
        val trendingAdapter = TrendingAdapter()
        trendingAdapter.submitList(trendingList)

        with(binding?.rvTrending) {
            this?.setHasFixedSize(true)
            this?.adapter = trendingAdapter
        }
    }

    private fun bindNowPlayingData(movieList: List<MovieEntity>) {
        val nowPlayingAdapter = NowPlayingAdapter()
        nowPlayingAdapter.submitList(movieList)

        with(binding?.rvNewMovies) {
            this?.setHasFixedSize(true)
            this?.adapter = nowPlayingAdapter
        }
    }

    private fun bindTvPopularData(tvShowList: List<TVShowEntity>) {
        val tvPopularAdapter = TvPopularAdapter()
        tvPopularAdapter.submitList(tvShowList)

        with(binding?.rvTvseries) {
            this?.setHasFixedSize(true)
            this?.adapter = tvPopularAdapter
        }
    }
}