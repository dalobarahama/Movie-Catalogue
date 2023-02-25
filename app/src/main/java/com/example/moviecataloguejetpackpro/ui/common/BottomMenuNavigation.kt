package com.example.moviecataloguejetpackpro.ui.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.ui.home.HomeFragment
import com.example.moviecataloguejetpackpro.ui.movie.MovieFragment
import com.example.moviecataloguejetpackpro.ui.tvShow.TVShowsFragment
import com.google.android.material.navigation.NavigationBarView

class BottomMenuNavigation(private val activity: AppCompatActivity) {

    fun defaultMenu() {
        activity.supportFragmentManager.beginTransaction().replace(R.id.view_pager, HomeFragment()).commit()
    }

    fun navigation(): NavigationBarView.OnItemSelectedListener {
        val navListener = NavigationBarView.OnItemSelectedListener {
            lateinit var selectedFragment: Fragment
            when (it.itemId) {
                R.id.home -> {
                    selectedFragment = HomeFragment()
                }
                R.id.movie -> {
                    selectedFragment = MovieFragment()
                }
                R.id.tv_show -> {
                    selectedFragment = TVShowsFragment()
                }
            }
            activity.supportFragmentManager.beginTransaction().replace(R.id.view_pager, selectedFragment).commit()
            true
        }

        return navListener
    }

}