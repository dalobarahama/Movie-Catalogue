package com.example.moviecataloguejetpackpro.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.room.Dao
import com.example.moviecataloguejetpackpro.data.source.remote.response.Result
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.FetchMovieUseCase
import com.example.moviecataloguejetpackpro.databinding.FragmentMoviesBinding
import com.example.moviecataloguejetpackpro.ui.common.BaseFragment
import com.example.moviecataloguejetpackpro.ui.detail.DetailActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieFragment : BaseFragment(), MovieAdapterRV.OnClick {
    private var _fragmentMoviesBinding: FragmentMoviesBinding? = null
    private val binding get() = _fragmentMoviesBinding

    @Inject
    lateinit var fetchMovieUseCase: FetchMovieUseCase

    @Inject
    lateinit var dao: Dao

    @Inject
    lateinit var activity: AppCompatActivity

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

    private fun fetchMoviesFromApi() {
        coroutineScope.launch {
            showLoading()
            try {
                when (val result = fetchMovieUseCase.fetchUpcomingMovies()) {
                    is Result.Success -> {
                        showData(result.responseList)
                    }
                    is Result.Failure -> onFetchFailed()
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
        movieAdapter.setOnClick(this)

        with(binding?.moviesRecyclerview) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = movieAdapter
        }
    }

    private fun showLoading() {
        binding?.progressBar?.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding?.progressBar?.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentMoviesBinding = null
    }

    override fun onItemClick(id: Int) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.EXTRA_MOVIE_TYPE)
        intent.putExtra(DetailActivity.EXTRA_ENTITY, id)
        startActivity(intent)
    }
}