package com.example.moviecataloguejetpackpro.ui.tvShow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecataloguejetpackpro.databinding.FragmentTvShowsBinding

class TVShowsFragment : Fragment() {
    private lateinit var fragmentTvShowsBinding: FragmentTvShowsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowsBinding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return fragmentTvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                requireActivity(),
                ViewModelProvider.NewInstanceFactory()
            )[TVShowViewModel::class.java]
            val tvShowAdapter = TVShowAdapter()
            tvShowAdapter.setMovies(viewModel.getTVShow())

            with(fragmentTvShowsBinding.moviesRecyclerview) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}