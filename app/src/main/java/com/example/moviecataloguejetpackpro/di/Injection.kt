package com.example.moviecataloguejetpackpro.di

import android.content.Context
import com.example.moviecataloguejetpackpro.data.source.Repository
import com.example.moviecataloguejetpackpro.data.source.remote.RemoteDataSource
import com.example.moviecataloguejetpackpro.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): Repository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return Repository.getInstance(remoteDataSource)
    }
}