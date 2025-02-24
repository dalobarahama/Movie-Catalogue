package com.example.moviecataloguejetpackpro.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.TrendingEntity
import com.facebook.shimmer.ShimmerFrameLayout

open class HomeFragmentMvcImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) : HomeFragmentMvc {

    interface Listener {
        fun onItemClicked()
    }

    private val listeners = HashSet<Listener>()

    private val rootView = layoutInflater.inflate(R.layout.fragment_home, parent)

    private val rvTrending: RecyclerView
    private val rvNewMovies: RecyclerView
    private val rvTvSeries: RecyclerView
    private val trendingShimmer: ShimmerFrameLayout
    private val newMoviesShimmer: ShimmerFrameLayout
    private val tvSeriesShimmer: ShimmerFrameLayout

    init {
        rvTrending = findViewById(R.id.rv_trending)
        rvNewMovies = findViewById(R.id.rv_new_movies)
        rvTvSeries = findViewById(R.id.rv_tvseries)
        trendingShimmer = findViewById(R.id.shimmer_layout_trending)
        newMoviesShimmer = findViewById(R.id.shimmer_layout_new_movies)
        tvSeriesShimmer = findViewById(R.id.shimmer_layout_tv_series)
    }

    override fun onFetchFailed() {
        Toast.makeText(getRootView().context, "Fetch Failed", Toast.LENGTH_SHORT).show()
    }

    override fun bindTrendingData(trendingList: List<TrendingEntity>) {
        val trendingAdapter = TrendingAdapter()
        trendingAdapter.submitList(trendingList)

        with(rvTrending) {
            this.setHasFixedSize(true)
            this.adapter = trendingAdapter
        }
    }

    override fun bindNowPlayingData(movieList: List<MovieEntity>) {
        val nowPlayingAdapter = NowPlayingAdapter()
        nowPlayingAdapter.submitList(movieList)

        with(rvNewMovies) {
            this.setHasFixedSize(true)
            this.adapter = nowPlayingAdapter
        }
    }

    override fun bindTvPopularData(tvShowList: List<TVShowEntity>) {
        val tvPopularAdapter = TvPopularAdapter()
        tvPopularAdapter.submitList(tvShowList)

        with(rvTvSeries) {
            this.setHasFixedSize(true)
            this.adapter = tvPopularAdapter
        }
    }

    override fun getRootView(): View {
        return rootView
    }

    override fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    override fun showShimmerLayoutTrending() {
        trendingShimmer.visibility = View.VISIBLE
        trendingShimmer.startShimmer()
    }

    override fun hideShimmerLayoutTrending() {
        trendingShimmer.stopShimmer()
        trendingShimmer.visibility = View.GONE
    }

    override fun showShimmerLayoutNewMovies() {
        newMoviesShimmer.visibility = View.VISIBLE
        newMoviesShimmer.startShimmer()
    }

    override fun hideShimmerLayoutNewMovies() {
        newMoviesShimmer.visibility = View.GONE
        newMoviesShimmer.stopShimmer()
    }

    override fun showShimmerLayoutTvSeries() {
        tvSeriesShimmer.visibility = View.VISIBLE
        tvSeriesShimmer.startShimmer()
    }

    override fun hideShimmerLayoutTvSeries() {
        tvSeriesShimmer.visibility = View.GONE
        tvSeriesShimmer.stopShimmer()
    }

    private fun <T : View> findViewById(id: Int): T {
        return getRootView().findViewById(id)
    }
}