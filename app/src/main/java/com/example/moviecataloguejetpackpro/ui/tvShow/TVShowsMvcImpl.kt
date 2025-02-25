package com.example.moviecataloguejetpackpro.ui.tvShow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.ui.common.basemvc.BaseViewMvcObservable
import com.example.moviecataloguejetpackpro.ui.itemMovieTvShow.ItemMovieTvShowAdapter

class TVShowsMvcImpl(private val layoutInflater: LayoutInflater, parent: ViewGroup?) : BaseViewMvcObservable<TVShowsMvc.Listener>(), TVShowsMvc, ItemMovieTvShowAdapter.Listener<TVShowEntity> {

    private var rvTvShows: RecyclerView
    private var progressBar: ProgressBar

    init {
        setRootView(R.layout.fragment_tv_shows, layoutInflater, parent)

        rvTvShows = findViewById(R.id.tvshows_recyclerview)
        progressBar = findViewById(R.id.progress_bar)
    }

    override fun onFetchFailed() {
        Toast.makeText(getContext(), "Fetch Failed", Toast.LENGTH_SHORT).show()
    }

    override fun showData(tvShowList: List<TVShowEntity>) {
        val tvShowAdapterRV = ItemMovieTvShowAdapter(layoutInflater, this)
        tvShowAdapterRV.submitList(tvShowList)

        with(rvTvShows) {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.adapter = tvShowAdapterRV
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun onItemOnClicked(entity: TVShowEntity) {
        getListener()?.onItemOnClicked(entity)
    }
}