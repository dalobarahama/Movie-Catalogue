package com.example.moviecataloguejetpackpro.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TrendingEntity
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.FetchMovieUseCase
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.FetchNowPlayingMoviesUseCase
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.FetchTrendingUseCase
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.FetchTvShowUseCase
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
    lateinit var fetchNowPlayingMoviesUseCase: FetchNowPlayingMoviesUseCase

    override fun onStart() {
        super.onStart()
        fetchTrendingFromApi()
        fetchNowPlayingMoviesFromApi()
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
            showLoading()
            try {
                when (val result = fetchTrendingUseCase.fetchTrending()) {
                    is FetchTrendingUseCase.Result.Success -> bindTrendingData(result.trendingList)

                    is FetchTrendingUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                hideLoading()
            }
        }
    }

    private fun fetchNowPlayingMoviesFromApi() {
        coroutineScope.launch {
            showLoading()
            try {
                when (val result = fetchNowPlayingMoviesUseCase.fetchNowPlayingMovies()) {
                    is FetchNowPlayingMoviesUseCase.Result.Success -> bindNowPlayingData(result.movies)

                    is FetchNowPlayingMoviesUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                hideLoading()
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

    private fun showLoading() {
        binding?.progressBar?.visibility = View.VISIBLE
        binding?.constraintLayout?.visibility = View.GONE
    }

    private fun hideLoading() {
        binding?.progressBar?.visibility = View.GONE
        binding?.constraintLayout?.visibility = View.VISIBLE
    }
}