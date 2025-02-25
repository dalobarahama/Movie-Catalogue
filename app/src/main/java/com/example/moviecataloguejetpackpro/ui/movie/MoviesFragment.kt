package com.example.moviecataloguejetpackpro.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecataloguejetpackpro.data.source.local.entity.DetailEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.room.Dao
import com.example.moviecataloguejetpackpro.data.source.remote.response.Result
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.FetchMovieUseCase
import com.example.moviecataloguejetpackpro.ui.common.BaseFragment
import com.example.moviecataloguejetpackpro.ui.detail.DetailActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieFragment : BaseFragment(), MovieMvcObservable.Listener {
    @Inject
    lateinit var fetchMovieUseCase: FetchMovieUseCase

    @Inject
    lateinit var dao: Dao

    @Inject
    lateinit var activity: AppCompatActivity

    private lateinit var viewMvc: MovieMvcObservable

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        viewMvc = MovieMvcObservableBase(layoutInflater, null)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return viewMvc.getRootView()
    }

    private fun fetchMoviesFromApi() {
        coroutineScope.launch {
            viewMvc.showLoading()
            try {
                when (val result = fetchMovieUseCase.fetchUpcomingMovies()) {
                    is Result.Success -> {
                        viewMvc.showData(result.responseList)
                    }

                    is Result.Failure -> viewMvc.onFetchFailed()
                }
            } finally {
                viewMvc.hideLoading()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        fetchMoviesFromApi()
    }

    override fun onDestroy() {
        viewMvc.unregisterListener(this)
        super.onDestroy()
    }

    override fun onItemOnClicked(movieEntity: MovieEntity) {
        val detailEntity = DetailEntity(
            movieEntity.title,
            movieEntity.overview,
            movieEntity.releaseDate,
            movieEntity.voteAverage.toString(),
            movieEntity.posterPath
        )
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ENTITY, detailEntity)
        startActivity(intent)
    }
}