package com.example.moviecataloguejetpackpro.data.source

import com.example.moviecataloguejetpackpro.data.Entity

interface EntityDataSource {

    fun getAllMovies(): List<Entity>

    fun getAllTVShow(): List<Entity>
}