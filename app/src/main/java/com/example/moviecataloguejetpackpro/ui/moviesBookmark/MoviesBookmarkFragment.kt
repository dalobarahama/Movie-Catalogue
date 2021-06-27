package com.example.moviecataloguejetpackpro.ui.moviesBookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecataloguejetpackpro.databinding.FragmentMoviesBookmarkBinding
import com.example.moviecataloguejetpackpro.ui.viewModel.ViewModelFactory

class MoviesBookmarkFragment : Fragment() {
    private var _fragmentBookmarkMoviesBinding: FragmentMoviesBookmarkBinding? = null
    private val binding get() = _fragmentBookmarkMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _fragmentBookmarkMoviesBinding =
            FragmentMoviesBookmarkBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieBookmarkViewModel::class.java]

            val adapter = MovieBookmarkAdapter()
            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getBookmarkMovies().observe(requireActivity(), { movies ->
                binding?.progressBar?.visibility = View.GONE
                adapter.submitList(movies)
                adapter.notifyDataSetChanged()
            })

            binding?.moviesBookmarkRecyclerview?.layoutManager =
                LinearLayoutManager(requireActivity())
            binding?.moviesBookmarkRecyclerview?.setHasFixedSize(true)
            binding?.moviesBookmarkRecyclerview?.adapter = adapter
        }
    }
}