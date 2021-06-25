package com.example.moviecataloguejetpackpro.di

import android.content.Context
import com.example.moviecataloguejetpackpro.data.Repository
import com.example.moviecataloguejetpackpro.data.source.local.LocalDataSource
import com.example.moviecataloguejetpackpro.data.source.local.room.MovieTvShowDatabase
import com.example.moviecataloguejetpackpro.data.source.remote.RemoteDataSource
import com.example.moviecataloguejetpackpro.utils.AppExecutors
import com.example.moviecataloguejetpackpro.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): Repository {

        val database = MovieTvShowDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper())
        val localDataSource = LocalDataSource.getInstance(database.dao())
        val appExecutors = AppExecutors()

        return Repository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}