package com.example.moviecataloguejetpackpro.ui.itemMovieTvShow

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.ui.common.basemvc.BaseViewMvcObservable
import com.example.moviecataloguejetpackpro.utils.Utils

class ItemMovieTvShowMvcImpl<T : Any>(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseViewMvcObservable<ItemMovieTvShowMvc.Listener<T>>(), ItemMovieTvShowMvc<T> {

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    private var tvTitle: TextView
    private var tvOverview: TextView
    private var ivPoster: ImageView

    private lateinit var entity: T

    init {
        setRootView(R.layout.item_movie_tvshow, layoutInflater, parent)
        tvTitle = findViewById(R.id.title_item_movie_tvshow)
        tvOverview = findViewById(R.id.overview_item_movie_tvshow)
        ivPoster = findViewById(R.id.poster_item_movie_tvshow)

        getRootView().rootView.setOnClickListener {
            getListener()?.onItemClicked(entity)
        }
    }

    override fun bindData(entity: T) {
        when (entity) {
            is TVShowEntity -> {
                this.entity = entity

                val tvShowEntity = entity as TVShowEntity
                tvTitle.text = tvShowEntity.name
                tvOverview.text = tvShowEntity.overview
                Utils.loadImageWithPlaceholder(
                    getContext(),
                    IMAGE_BASE_URL + tvShowEntity.posterPath,
                    ivPoster,
                    R.drawable.placeholder_image
                )
            }

            is MovieEntity -> {
                this.entity = entity

                val movieEntity = entity as MovieEntity
                tvTitle.text = movieEntity.title
                tvOverview.text = movieEntity.overview
                Utils.loadImageWithPlaceholder(
                    getContext(),
                    IMAGE_BASE_URL + movieEntity.posterPath,
                    ivPoster,
                    R.drawable.placeholder_image
                )
            }
        }
    }

}