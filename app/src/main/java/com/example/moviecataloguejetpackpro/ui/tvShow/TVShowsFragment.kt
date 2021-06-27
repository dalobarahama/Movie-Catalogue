package com.example.moviecataloguejetpackpro.ui.tvShow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecataloguejetpackpro.databinding.FragmentTvShowsBinding
import com.example.moviecataloguejetpackpro.ui.viewModel.ViewModelFactory
import com.example.moviecataloguejetpackpro.vo.Status

class TVShowsFragment : Fragment() {
    private var _fragmentTvShowsBinding: FragmentTvShowsBinding? = null
    private val binding get() = _fragmentTvShowsBinding

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

            val tvShowAdapter = TVShowAdapter()
            viewModel.getAllTvShow().observe(requireActivity(), { tvShows ->
                if (tvShows != null) {
                    when (tvShows.status) {
                        Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding?.progressBar?.visibility = View.GONE
                            tvShowAdapter.submitList(tvShows.data)
                            Log.i("TAG", "onViewCreated: " + tvShows.data?.get(0)?.name)
                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding?.tvshowsRecyclerview) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = tvShowAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentTvShowsBinding = null
    }
}