package com.example.moviecataloguejetpackpro.ui.detail

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.data.source.local.entity.DetailEntity
import com.example.moviecataloguejetpackpro.utils.Utils.loadImage

class DetailActivityMvcImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) : DetailActivityMvc {

    interface Listener {
        fun onUpClicked()
    }

    private var listeners = HashSet<Listener>()

    private var rootView: View = layoutInflater.inflate(R.layout.activity_detail, parent, false)

    private var tvTitle: TextView
    private var tvOverview: TextView
    private var tvReleaseDate: TextView
    private var tvScore: TextView
    private var ivPoster: ImageView
    private var toolbar: Toolbar
    private var upButton: ImageView

    private var menu: Menu? = null

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    init {
        tvTitle = findViewById(R.id.title_detail_activity)
        tvOverview = findViewById(R.id.overview_detail_activity)
        tvReleaseDate = findViewById(R.id.release_date_detail_activity)
        tvScore = findViewById(R.id.score_detail_activity)
        ivPoster = findViewById(R.id.poster_detail_activity)
        toolbar = findViewById(R.id.toolbar)
        upButton = toolbar.findViewById(R.id.btn_up)

        upButton.setOnClickListener {
            for (listener in listeners) {
                listener.onUpClicked()
            }
        }
    }

    override fun getRootView(): View {
        return rootView
    }

    override fun populateTrendingEntity(detailEntity: DetailEntity) {
        tvTitle.text = detailEntity.title
        tvOverview.text = detailEntity.overview
        tvReleaseDate.text = detailEntity.releaseDate
        tvScore.text = detailEntity.voteAverage

        loadImage(
            getRootView().context, IMAGE_BASE_URL + detailEntity.posterPath, ivPoster
        )
    }

    override fun setBookmarkState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(
                getRootView().context, R.drawable.ic_baseline_bookmark_selected
            )
        } else {
            menuItem?.icon = ContextCompat.getDrawable(
                getRootView().context, R.drawable.ic_baseline_bookmark_unselected
            )
        }
    }

    override fun getToolbar(): Toolbar {
        return toolbar
    }

    override fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    private fun <T : View> findViewById(id: Int): T {
        return getRootView().findViewById(id)
    }
}