package com.example.moviecataloguejetpackpro.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.databinding.ActivityDetailBinding
import com.example.moviecataloguejetpackpro.ui.viewModel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ENTITY = "extra_entity"
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_MOVIE_TYPE = "movie"
        const val EXTRA_TV_SHOW_TYPE = "tv_show"
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    private lateinit var activityDetailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(activityDetailBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this,
            factory
        )[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val entityId = extras.getInt(EXTRA_ENTITY)
            val itemType = extras.getString(EXTRA_TYPE)
            activityDetailBinding.content.visibility = View.GONE
            activityDetailBinding.progressBar.visibility = View.VISIBLE
            if (itemType == EXTRA_MOVIE_TYPE) {
                viewModel.setMovieSelected(entityId)
                viewModel.getMovie().observe(this, { movie ->
                    activityDetailBinding.content.visibility = View.VISIBLE
                    activityDetailBinding.progressBar.visibility = View.GONE
                    populateMovieEntity(movie)
                })
            } else {
                viewModel.setTvShowSelected(entityId)
                viewModel.getTvShow().observe(this, { tvShow ->
                    activityDetailBinding.content.visibility = View.VISIBLE
                    activityDetailBinding.progressBar.visibility = View.GONE
                    populateTvShowEntity(tvShow)
                })
            }
        }
    }

    private fun populateMovieEntity(movieEntity: MovieEntity) {
        activityDetailBinding.titleDetailActivity.text = movieEntity.title
        activityDetailBinding.overviewDetailActivity.text = movieEntity.overview
        activityDetailBinding.releaseDateDetailActivity.text =
            movieEntity.releaseDate
        activityDetailBinding.scoreDetailActivity.text = movieEntity.voteAverage.toString()

        Glide.with(this)
            .load(IMAGE_BASE_URL + movieEntity.posterPath)
            .into(activityDetailBinding.posterDetailActivity)
    }

    private fun populateTvShowEntity(tvShowEntity: TVShowEntity) {
        activityDetailBinding.titleDetailActivity.text = tvShowEntity.name
        activityDetailBinding.overviewDetailActivity.text = tvShowEntity.overview
        activityDetailBinding.releaseDateDetailActivity.text =
            tvShowEntity.firstAirDate
        activityDetailBinding.scoreDetailActivity.text = tvShowEntity.voteAverage.toString()

        Glide.with(this)
            .load(IMAGE_BASE_URL + tvShowEntity.posterPath)
            .into(activityDetailBinding.posterDetailActivity)
    }

}