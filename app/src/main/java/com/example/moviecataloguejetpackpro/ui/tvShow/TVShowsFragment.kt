package com.example.moviecataloguejetpackpro.ui.tvShow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.remote.ApiService
import com.example.moviecataloguejetpackpro.data.source.remote.response.TVShowResponse
import com.example.moviecataloguejetpackpro.databinding.FragmentTvShowsBinding
import com.example.moviecataloguejetpackpro.ui.common.BaseFragment
import com.example.moviecataloguejetpackpro.ui.viewModel.ViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowsFragment : BaseFragment() {
    private var _fragmentTvShowsBinding: FragmentTvShowsBinding? = null
    private val binding get() = _fragmentTvShowsBinding

    private lateinit var apiService: ApiService

    private lateinit var tvShowAdapterRV: TVShowAdapterRV

    private var tvShowList: List<TVShowEntity> = emptyList()

    override fun onStart() {
        super.onStart()
        fetchTvShowsFromApi()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        apiService = appComposition.apiService
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
        binding?.progressBar!!.visibility = View.VISIBLE
        val client = apiService.getTVOnTheAir()
        client.enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(
                call: Call<TVShowResponse>,
                response: Response<TVShowResponse>,
            ) {
                binding?.progressBar!!.visibility = View.GONE
                tvShowList = response.body()?.tvShows ?: emptyList()

                showData(tvShowList)
            }

            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                binding?.progressBar!!.visibility = View.GONE
                Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
            }

        })
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

    override fun onDestroy() {
        super.onDestroy()
        _fragmentTvShowsBinding = null
    }
}