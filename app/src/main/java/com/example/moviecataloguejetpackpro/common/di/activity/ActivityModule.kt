package com.example.moviecataloguejetpackpro.common.di.activity

import androidx.appcompat.app.AppCompatActivity
import com.example.moviecataloguejetpackpro.data.source.local.room.MovieTvShowDatabase
import com.example.moviecataloguejetpackpro.ui.common.BottomMenuNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: AppCompatActivity) {

    @Provides
    fun activity(): AppCompatActivity = activity

    @Provides
    fun dao(movieTvShowDatabase: MovieTvShowDatabase) = movieTvShowDatabase.dao()

    @Provides
    fun menuNavigation(activity: AppCompatActivity) = BottomMenuNavigation(activity)
}