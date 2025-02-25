package com.example.moviecataloguejetpackpro.common.di.presentation

import com.example.moviecataloguejetpackpro.ui.detail.DetailActivity
import com.example.moviecataloguejetpackpro.ui.home.HomeFragment
import com.example.moviecataloguejetpackpro.ui.main.MainActivity
import com.example.moviecataloguejetpackpro.ui.movie.MoviesFragment
import com.example.moviecataloguejetpackpro.ui.tvShow.TVShowsFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(fragment: TVShowsFragment)
    fun inject(fragment: MoviesFragment)
    fun inject(fragment: HomeFragment)

    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
}