package com.example.moviecataloguejetpackpro.ui.itemMovieTvShow

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.ui.common.basemvc.BaseViewMvcObservable

class ItemMovieTvShowMvcImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseViewMvcObservable<ItemMovieTvShowMvc.Listener>(), ItemMovieTvShowMvc {

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    private var tvTitle: TextView
    private var tvOverview: TextView
    private var ivPoster: ImageView

    private lateinit var movieEntity: MovieEntity

    init {
        setRootView(R.layout.item_movie_tvshow, layoutInflater, parent)
        tvTitle = findViewById(R.id.title_item_movie_tvshow)
        tvOverview = findViewById(R.id.overview_item_movie_tvshow)
        ivPoster = findViewById(R.id.poster_item_movie_tvshow)

        getRootView().rootView.setOnClickListener {
            getListener()?.onItemClicked(movieEntity)
        }
    }

    override fun bindData(movieEntity: MovieEntity) {
        this.movieEntity = movieEntity

        tvTitle.text = movieEntity.title
        tvOverview.text = movieEntity.overview
        Glide.with(getContext())
            .load(IMAGE_BASE_URL + movieEntity.posterPath)
            .into(ivPoster)
    }

}