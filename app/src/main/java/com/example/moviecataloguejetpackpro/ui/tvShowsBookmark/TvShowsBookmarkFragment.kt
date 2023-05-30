package com.example.moviecataloguejetpackpro.ui.tvShowsBookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecataloguejetpackpro.databinding.FragmentTvShowsBookmarkBinding

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

            val adapter = TvShowBookmarkAdapter()
            binding?.progressBar?.visibility = View.VISIBLE

            binding?.tvShowsBookmarkRecyclerview?.layoutManager =
                LinearLayoutManager(requireActivity())
            binding?.tvShowsBookmarkRecyclerview?.setHasFixedSize(true)
            binding?.tvShowsBookmarkRecyclerview?.adapter = adapter
        }
    }
}