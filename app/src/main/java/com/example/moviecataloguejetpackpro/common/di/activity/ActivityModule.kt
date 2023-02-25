package com.example.moviecataloguejetpackpro.common.di.activity

import androidx.appcompat.app.AppCompatActivity
import com.example.moviecataloguejetpackpro.data.source.local.room.MovieTvShowDatabase
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: AppCompatActivity) {

    @Provides
    fun activity() = activity

    @Provides
    fun dao(movieTvShowDatabase: MovieTvShowDatabase) = movieTvShowDatabase.dao()
}