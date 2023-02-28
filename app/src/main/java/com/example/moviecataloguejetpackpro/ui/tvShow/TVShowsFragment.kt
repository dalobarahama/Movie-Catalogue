package com.example.moviecataloguejetpackpro.ui.tvShow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.remote.usecase.FetchTvShowUseCase
import com.example.moviecataloguejetpackpro.databinding.FragmentTvShowsBinding
import com.example.moviecataloguejetpackpro.ui.common.BaseFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class TVShowsFragment : BaseFragment() {
    private var _fragmentTvShowsBinding: FragmentTvShowsBinding? = null
    private val binding get() = _fragmentTvShowsBinding

    @Inject
    lateinit var fetchTvShowUseCase: FetchTvShowUseCase

    override fun onStart() {
        super.onStart()
        fetchTvShowsFromApi()
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
        _fragmentTvShowsBinding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun fetchTvShowsFromApi() {
        coroutineScope.launch {
            showLoading()
            try {
                when (val result = fetchTvShowUseCase.fetchTvOnTheAir()) {
                    is FetchTvShowUseCase.Result.Success -> {
                        showData(result.tvShows)
                    }
                    is FetchTvShowUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                hideLoading()
            }
        }
    }

    private fun onFetchFailed() {
        Toast.makeText(requireContext(), "Fetch Failed", Toast.LENGTH_SHORT).show()
    }

    private fun showData(tvShowList: List<TVShowEntity>) {
        val tvShowAdapterRV = TVShowAdapterRV()
        tvShowAdapterRV.submitList(tvShowList)

        with(binding?.tvshowsRecyclerview) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = tvShowAdapterRV
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
        _fragmentTvShowsBinding = null
    }
}