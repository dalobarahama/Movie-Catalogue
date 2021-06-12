package com.example.moviecataloguejetpackpro.ui.tvShow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecataloguejetpackpro.databinding.FragmentTvShowsBinding

class TVShowsFragment : Fragment() {
    private lateinit var fragmentTvShowsBinding: FragmentTvShowsBinding
    private val tvShowViewModel: TVShowViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragmentTvShowsBinding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return fragmentTvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
//            val viewModel = ViewModelProvider(
//                requireActivity(),
//                ViewModelProvider.NewInstanceFactory()
//            )[TVShowViewModel::class.java]
            val tvShowAdapter = TVShowAdapter()

            tvShowViewModel.tvShowList.observe(requireActivity(), { tvShows ->
                Log.i("TAG", "onViewCreated: " + tvShows[0].originalName)
                tvShowAdapter.setTvShows(tvShows)
                tvShowAdapter.notifyDataSetChanged()
            })

            with(fragmentTvShowsBinding.tvshowsRecyclerview) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}