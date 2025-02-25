package com.example.moviecataloguejetpackpro.ui.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.ui.common.basemvc.BaseViewMvcObservable

class MovieMvcObservableBase(layoutInflater: LayoutInflater, parent: ViewGroup?) : BaseViewMvcObservable<MovieMvcObservable.Listener>(), MovieMvcObservable {

    private val rootView = layoutInflater.inflate(R.layout.fragment_movies, parent, false)

    private var rvMovies: RecyclerView
    private var progressBar: ProgressBar

    init {
        rvMovies = findViewById(R.id.movies_recyclerview)
        progressBar = findViewById(R.id.progress_bar)
    }

    override fun showData(movieList: List<MovieEntity>) {
        val movieAdapter = MovieAdapterRV()
        movieAdapter.submitList(movieList)

        with(rvMovies) {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.adapter = movieAdapter
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun onFetchFailed() {
        Toast.makeText(getContext(), "Fetch Failed", Toast.LENGTH_SHORT).show()
    }

    override fun getRootView(): View {
        return rootView
    }

    override fun getContext(): Context {
        return getRootView().context
    }
}