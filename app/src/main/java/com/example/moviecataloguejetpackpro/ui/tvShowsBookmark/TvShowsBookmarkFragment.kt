package com.example.moviecataloguejetpackpro.ui.tvShowsBookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecataloguejetpackpro.databinding.FragmentTvShowsBookmarkBinding
import com.example.moviecataloguejetpackpro.ui.viewModel.ViewModelFactory

class TvShowsBookmarkFragment : Fragment() {
    private var _fragmentBookmarkTvShowsBinding: FragmentTvShowsBookmarkBinding? = null
    private val binding get() = _fragmentBookmarkTvShowsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _fragmentBookmarkTvShowsBinding =
            FragmentTvShowsBookmarkBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowBookmarkViewModel::class.java]

            val adapter = TvShowBookmarkAdapter()
            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getBookmarkTvShows().observe(requireActivity(), { tvShows ->
                binding?.progressBar?.visibility = View.GONE
                adapter.submitList(tvShows)
                adapter.notifyDataSetChanged()
            })

            binding?.moviesRecyclerview?.layoutManager = LinearLayoutManager(requireActivity())
            binding?.moviesRecyclerview?.setHasFixedSize(true)
            binding?.moviesRecyclerview?.adapter = adapter
        }
    }
}