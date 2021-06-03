package com.example.moviecataloguejetpackpro.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_OVERVIEW = "extra_overview"
        const val EXTRA_TAGS = "extra_tags"
        const val EXTRA_SCORE = "extra_score"
        const val EXTRA_RELEASE_DATE = "extra_release_date"
        const val EXTRA_IMAGE_PATH = "extra_image_path"
    }

    private lateinit var activityDetailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(activityDetailBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val itemType = extras.getString(EXTRA_TYPE)
            val itemId = extras.getString(EXTRA_ID)
            if (itemId != null && itemType != null) {
                viewModel.setSelectedItem(itemType, itemId)
                populateEntity(viewModel.getItem())
            }
        }
    }

    private fun populateEntity(movieEntity: MovieEntity) {
        activityDetailBinding.titleDetailActivity.text = movieEntity.title
        activityDetailBinding.overviewDetailActivity.text = movieEntity.overview
        activityDetailBinding.releaseDateDetailActivity.text =
            movieEntity.releaseDate
        activityDetailBinding.scoreDetailActivity.text = movieEntity.score
        activityDetailBinding.tagsDetailActivity.text = movieEntity.tags

        Glide.with(this)
            .load(movieEntity.imagePath)
            .into(activityDetailBinding.posterDetailActivity)
    }

}