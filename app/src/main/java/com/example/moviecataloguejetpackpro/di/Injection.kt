package com.example.moviecataloguejetpackpro.di

import com.example.moviecataloguejetpackpro.data.source.Repository
import com.example.moviecataloguejetpackpro.data.source.remote.RemoteDataSource
import com.example.moviecataloguejetpackpro.utils.JsonHelper

object Injection {
    fun provideRepository(): Repository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper())

        return Repository.getInstance(remoteDataSource)
    }
}