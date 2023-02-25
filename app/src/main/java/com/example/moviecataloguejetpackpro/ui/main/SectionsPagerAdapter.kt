package com.example.moviecataloguejetpackpro.ui.main

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.ui.home.HomeFragment
import com.example.moviecataloguejetpackpro.ui.movie.MovieFragment
import com.example.moviecataloguejetpackpro.ui.moviesBookmark.MoviesBookmarkFragment
import com.example.moviecataloguejetpackpro.ui.tvShow.TVShowsFragment
import com.example.moviecataloguejetpackpro.ui.tvShowsBookmark.TvShowsBookmarkFragment

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES =
            intArrayOf(R.string.movies,
                R.string.tv_shows,
                R.string.movies_bookmark,
                R.string.tv_shows_bookmark)
    }

    override fun getCount(): Int = 4

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment()
            1 -> TVShowsFragment()
            2 -> MoviesBookmarkFragment()
            3 -> TvShowsBookmarkFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        context.resources.getString(TAB_TITLES[position])
}