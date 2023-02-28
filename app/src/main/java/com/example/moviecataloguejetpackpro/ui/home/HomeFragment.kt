package com.example.moviecataloguejetpackpro.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.FetchMovieUseCase
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.FetchTvShowUseCase
import com.example.moviecataloguejetpackpro.databinding.FragmentHomeBinding
import com.example.moviecataloguejetpackpro.ui.common.BaseFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : BaseFragment() {
    private var _fragmentBinding: FragmentHomeBinding? = null
    private val binding get() = _fragmentBinding

    @Inject
    lateinit var fetchMovieUseCase: FetchMovieUseCase

    @Inject
    lateinit var fetchTvShowUseCase: FetchTvShowUseCase

    override fun onStart() {
        super.onStart()
        fetchMovieFromApi()
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

    private fun fetchMovieFromApi() {
        coroutineScope.launch {
            showLoading()
            try {
                when (val result = fetchMovieUseCase.fetchUpcomingMovies()) {
                    is FetchMovieUseCase.Result.Success -> bindData(result.movies)

                    is FetchMovieUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                hideLoading()
            }
        }
    }

    private fun onFetchFailed() {
        Toast.makeText(requireContext(), "Fetch Failed", Toast.LENGTH_SHORT).show()
    }

    private fun bindData(movies: List<MovieEntity>) {
        val homeAdapter = HomeAdapter()
        homeAdapter.submitList(movies)

        with(binding?.rvTrending) {
            this?.setHasFixedSize(true)
            this?.adapter = homeAdapter
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