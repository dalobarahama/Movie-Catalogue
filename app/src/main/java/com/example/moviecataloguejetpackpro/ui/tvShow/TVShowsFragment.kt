package com.example.moviecataloguejetpackpro.ui.tvShow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviecataloguejetpackpro.data.source.local.entity.DetailEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.FetchTvShowUseCase
import com.example.moviecataloguejetpackpro.ui.common.BaseFragment
import com.example.moviecataloguejetpackpro.ui.detail.DetailActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class TVShowsFragment : BaseFragment(), TVShowsMvc.Listener {
    @Inject
    lateinit var fetchTvShowUseCase: FetchTvShowUseCase

    private lateinit var viewMvc: TVShowsMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        viewMvc = TVShowsMvcImpl(layoutInflater, null)
        fetchTvShowsFromApi()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return viewMvc.getRootView()
    }

    private fun fetchTvShowsFromApi() {
        coroutineScope.launch {
            viewMvc.showLoading()
            try {
                when (val result = fetchTvShowUseCase.fetchTvOnTheAir()) {
                    is FetchTvShowUseCase.Result.Success -> {
                        viewMvc.showData(result.tvShows)
                    }

                    is FetchTvShowUseCase.Result.Failure -> viewMvc.onFetchFailed()
                }
            } finally {
                viewMvc.hideLoading()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
    }

    override fun onDestroy() {
        viewMvc.unregisterListener(this)
        super.onDestroy()
    }

    override fun onItemOnClicked(tvShowEntity: TVShowEntity) {
        val entity = DetailEntity(
            tvShowEntity.originalName,
            tvShowEntity.overview,
            tvShowEntity.firstAirDate,
            tvShowEntity.voteAverage.toString(),
            tvShowEntity.posterPath
        )

        startActivity(Intent(activity, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_ENTITY, entity)
        })
    }
}