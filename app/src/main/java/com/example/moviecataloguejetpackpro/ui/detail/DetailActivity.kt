package com.example.moviecataloguejetpackpro.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntityLocal
import com.example.moviecataloguejetpackpro.data.source.local.entity.TvShowEntityLocal
import com.example.moviecataloguejetpackpro.databinding.ActivityDetailBinding
import com.example.moviecataloguejetpackpro.ui.viewModel.ViewModelFactory
import com.example.moviecataloguejetpackpro.vo.Status

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ENTITY = "extra_entity"
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_MOVIE_TYPE = "movie"
        const val EXTRA_TV_SHOW_TYPE = "tv_show"
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    private lateinit var activityDetailBinding: ActivityDetailBinding

    private lateinit var viewModel: DetailViewModel
    private var menu: Menu? = null

    private var entityId: Int = 0
    private lateinit var itemType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(activityDetailBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this,
            factory
        )[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            entityId = extras.getInt(EXTRA_ENTITY)
            itemType = extras.getString(EXTRA_TYPE).toString()
            activityDetailBinding.content.visibility = View.GONE
            activityDetailBinding.progressBar.visibility = View.VISIBLE
            if (itemType == EXTRA_MOVIE_TYPE) {
                viewModel.setMovieSelected(entityId)
                viewModel.getMovie.observe(this, { movie ->
                    if (movie != null) {
                        when (movie.status) {
                            Status.LOADING -> activityDetailBinding.progressBar.visibility =
                                View.VISIBLE
                            Status.SUCCESS -> if (movie.data != null) {
                                activityDetailBinding.progressBar.visibility = View.GONE
                                activityDetailBinding.content.visibility = View.VISIBLE

                                populateMovieEntity(movie.data)
                            }
                            Status.ERROR -> {
                                activityDetailBinding.progressBar.visibility = View.GONE
                                Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            } else {
                viewModel.setTvShowSelected(entityId)
                viewModel.getTvShow.observe(this, { tvShow ->
                    if (tvShow != null) {
                        when (tvShow.status) {
                            Status.LOADING -> activityDetailBinding.progressBar.visibility =
                                View.VISIBLE
                            Status.SUCCESS -> if (tvShow.data != null) {
                                activityDetailBinding.progressBar.visibility = View.GONE
                                activityDetailBinding.content.visibility = View.VISIBLE

                                populateTvShowEntity(tvShow.data)
                            }
                            Status.ERROR -> {
                                activityDetailBinding.progressBar.visibility = View.GONE
                                Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                })
            }
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
        if (itemType == EXTRA_MOVIE_TYPE) {
            viewModel.setMovieSelected(entityId)
            viewModel.getMovie.observe(this, { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> activityDetailBinding.progressBar.visibility =
                            View.VISIBLE
                        Status.SUCCESS -> if (movie.data != null) {
                            activityDetailBinding.progressBar.visibility = View.GONE
                            val state = movie.data.bookmarked
                            setBookmarkState(state)
                        }
                        Status.ERROR -> {
                            activityDetailBinding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        } else {
            viewModel.setTvShowSelected(entityId)
            viewModel.getTvShow.observe(this, { tvShow ->
                if (tvShow != null) {
                    when (tvShow.status) {
                        Status.LOADING -> activityDetailBinding.progressBar.visibility =
                            View.VISIBLE
                        Status.SUCCESS -> if (tvShow.data != null) {
                            activityDetailBinding.progressBar.visibility = View.GONE
                            val state = tvShow.data.bookmarked
                            setBookmarkState(state)
                        }
                        Status.ERROR -> {
                            activityDetailBinding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            return if (itemType == EXTRA_MOVIE_TYPE) {
                viewModel.setMovieBookmark()
                true
            } else {
                viewModel.setTvShowBookmark()
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