package com.example.moviecataloguejetpackpro.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecataloguejetpackpro.databinding.FragmentMoviesBinding

class MovieFragment : Fragment() {
    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
    private val movieViewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(inflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
//            val viewModel = ViewModelProvider(
//                requireActivity(),
//                ViewModelProvider.NewInstanceFactory()
//            )[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()

            movieViewModel.movieList.observe(requireActivity(), { movies ->
                movieAdapter.setMovies(movies)
                movieAdapter.notifyDataSetChanged()
            })

            with(fragmentMoviesBinding.moviesRecyclerview) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

}