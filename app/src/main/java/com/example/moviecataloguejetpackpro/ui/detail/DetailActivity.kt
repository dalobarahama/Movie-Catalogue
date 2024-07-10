package com.example.moviecataloguejetpackpro.ui.detail

import android.os.Bundle
import android.view.Menu
import androidx.core.content.ContextCompat
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.data.source.local.entity.DetailEntity
import com.example.moviecataloguejetpackpro.databinding.ActivityDetailBinding
import com.example.moviecataloguejetpackpro.ui.common.BaseActivity

class DetailActivity : BaseActivity() {

    companion object {
        const val EXTRA_ENTITY = "extra_entity"
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    private lateinit var activityDetailBinding: ActivityDetailBinding

    private var menu: Menu? = null

    private lateinit var itemType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)

        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(activityDetailBinding.root)

        val toolbar = activityDetailBinding.toolbar
        setSupportActionBar(toolbar.root)
        toolbar.btnUp.setOnClickListener {
            onBackPressed()
        }

        val detailEntity = intent?.getParcelableExtra<DetailEntity>(EXTRA_ENTITY)
        if (detailEntity != null) {
            populateTrendingEntity(detailEntity)
        }

    }

    private fun populateTrendingEntity(detailEntity: DetailEntity) {
        activityDetailBinding.titleDetailActivity.text = detailEntity.title
        activityDetailBinding.overviewDetailActivity.text = detailEntity.overview
        activityDetailBinding.releaseDateDetailActivity.text = detailEntity.releaseDate
        activityDetailBinding.scoreDetailActivity.text = detailEntity.voteAverage

        loadImage(
            IMAGE_BASE_URL + detailEntity.posterPath,
            activityDetailBinding.posterDetailActivity
        )
    }

    private fun setBookmarkState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state) {
            menuItem?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_baseline_bookmark_selected)
        } else {
            menuItem?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_baseline_bookmark_unselected)
        }
    }
}