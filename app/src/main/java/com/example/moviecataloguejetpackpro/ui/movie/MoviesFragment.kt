package com.example.moviecataloguejetpackpro.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.remote.FetchMovieUseCase
import com.example.moviecataloguejetpackpro.databinding.FragmentMoviesBinding
import com.example.moviecataloguejetpackpro.ui.common.BaseFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieFragment : BaseFragment() {
    private var _fragmentMoviesBinding: FragmentMoviesBinding? = null
    private val binding get() = _fragmentMoviesBinding

    @Inject
    lateinit var fetchMovieUseCase: FetchMovieUseCase

    override fun onStart() {
        super.onStart()
        fetchMoviesFromApi()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _fragmentMoviesBinding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun fetchMoviesFromApi() {
        coroutineScope.launch {
            showLoading()
            try {
                when (val result = fetchMovieUseCase.fetchUpcomingMovies()) {
                    is FetchMovieUseCase.Result.Success -> {
                        showData(result.movies)
                    }
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

    private fun showData(movieList: List<MovieEntity>) {
        val movieAdapter = MovieAdapterRV()
        movieAdapter.submitList(movieList)

        with(binding?.moviesRecyclerview) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = movieAdapter
        }
    }

    private fun showLoading() {
        binding?.progressBar!!.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding?.progressBar!!.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentMoviesBinding = null
    }

}