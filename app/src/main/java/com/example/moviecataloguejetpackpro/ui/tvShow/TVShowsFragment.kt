package com.example.moviecataloguejetpackpro.ui.tvShow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.remote.FetchTvShowUseCase
import com.example.moviecataloguejetpackpro.databinding.FragmentTvShowsBinding
import com.example.moviecataloguejetpackpro.ui.common.BaseFragment
import com.example.moviecataloguejetpackpro.ui.viewModel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class TVShowsFragment : BaseFragment() {
    private var _fragmentTvShowsBinding: FragmentTvShowsBinding? = null
    private val binding get() = _fragmentTvShowsBinding

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    @Inject
    lateinit var fetchTvShowUseCase: FetchTvShowUseCase

    private lateinit var tvShowAdapterRV: TVShowAdapterRV

    private var tvShowList: List<TVShowEntity> = emptyList()

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
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TVShowViewModel::class.java]

//            viewModel.getAllTvShow().observe(requireActivity()) { tvShows ->
//                if (tvShows != null) {
//                    when (tvShows.status) {
//                        Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
//                        Status.SUCCESS -> {
//                            binding?.progressBar?.visibility = View.GONE
//                            tvShowAdapter.submitList(tvShows.data)
//                            Log.i("TAG", "onViewCreated: " + tvShows.data?.get(0)?.name)
//                        }
//                        Status.ERROR -> {
//                            binding?.progressBar?.visibility = View.GONE
//                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//            }


        }
    }

    private fun fetchTvShowsFromApi() {
        Log.d("fetchTvShow", "fetchTvShowsFromApi called")
        coroutineScope.launch {
            showLoading()
            try {
                val result = fetchTvShowUseCase.fetchTvOnTheAir()
                when (result) {
                    is FetchTvShowUseCase.Result.Success -> {
                        Log.d("fetchTvShow", "fetchTvShowsFromApi: ${result.tvShows.size}")
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
        tvShowAdapterRV = TVShowAdapterRV()
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