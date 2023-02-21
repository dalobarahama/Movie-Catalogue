package com.example.moviecataloguejetpackpro.common.di.presentation

import com.example.moviecataloguejetpackpro.ui.movie.MovieFragment
import com.example.moviecataloguejetpackpro.ui.tvShow.TVShowsFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(fragment: TVShowsFragment)
    fun inject(fragment: MovieFragment)
}