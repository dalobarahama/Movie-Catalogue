package com.example.moviecataloguejetpackpro.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_OVERVIEW = "extra_overview"
        const val EXTRA_TAGS = "extra_tags"
        const val EXTRA_SCORE = "extra_score"
        const val EXTRA_RELEASE_DATE = "extra_release_date"
        const val EXTRA_IMAGE_PATH = "extra_image_path"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(activityDetailBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            activityDetailBinding.titleDetailActivity.text = extras.getString(EXTRA_TITLE)
            activityDetailBinding.overviewDetailActivity.text = extras.getString(EXTRA_OVERVIEW)
            activityDetailBinding.releaseDateDetailActivity.text =
                extras.getString(EXTRA_RELEASE_DATE)
            activityDetailBinding.scoreDetailActivity.text = extras.getString(EXTRA_RELEASE_DATE)

            Glide.with(this)
                .load(extras.getInt(EXTRA_IMAGE_PATH))
                .into(activityDetailBinding.posterDetailActivity)
        }
    }
}