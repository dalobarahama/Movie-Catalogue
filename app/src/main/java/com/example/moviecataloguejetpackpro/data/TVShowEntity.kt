package com.example.moviecataloguejetpackpro.data

data class TVShowEntity(
    var tvShowId: String,
    var title: String,
    var overview: String,
    var tags: String,
    var score: String,
    var releaseDate: String,
    var imagePath: Int
)