package com.example.moviecataloguejetpackpro.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntityLocal
import com.example.moviecataloguejetpackpro.data.source.local.entity.TvShowEntityLocal
import com.example.moviecataloguejetpackpro.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ENTITY = "extra_entity"
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_MOVIE_TYPE = "movie"
        const val EXTRA_TV_SHOW_TYPE = "tv_show"
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    private lateinit var activityDetailBinding: ActivityDetailBinding

    private var menu: Menu? = null

    private var entityId: Int = 0
    private lateinit var itemType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(activityDetailBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val extras = intent.extras
        if (extras != null) {
            entityId = extras.getInt(EXTRA_ENTITY)
            itemType = extras.getString(EXTRA_TYPE).toString()
            activityDetailBinding.content.visibility = View.GONE
            activityDetailBinding.progressBar.visibility = View.VISIBLE

        }
    }

    private fun populateMovieEntity(movieEntity: MovieEntityLocal) {
        activityDetailBinding.titleDetailActivity.text = movieEntity.title
        activityDetailBinding.overviewDetailActivity.text = movieEntity.overview
        activityDetailBinding.releaseDateDetailActivity.text =
            movieEntity.releaseDate
        activityDetailBinding.scoreDetailActivity.text = movieEntity.voteAverage.toString()

        Glide.with(this)
            .load(IMAGE_BASE_URL + movieEntity.posterPath)
            .into(activityDetailBinding.posterDetailActivity)
    }

    private fun populateTvShowEntity(tvShowEntity: TvShowEntityLocal) {
        activityDetailBinding.titleDetailActivity.text = tvShowEntity.name
        activityDetailBinding.overviewDetailActivity.text = tvShowEntity.overview
        activityDetailBinding.releaseDateDetailActivity.text =
            tvShowEntity.firstAirDate
        activityDetailBinding.scoreDetailActivity.text = tvShowEntity.voteAverage.toString()

        Glide.with(this)
            .load(IMAGE_BASE_URL + tvShowEntity.posterPath)
            .into(activityDetailBinding.posterDetailActivity)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            return if (itemType == EXTRA_MOVIE_TYPE) {
                true
            } else {
                true
            }
        }
        return super.onOptionsItemSelected(item)
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