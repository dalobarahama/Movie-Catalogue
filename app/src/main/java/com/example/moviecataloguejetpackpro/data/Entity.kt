package com.example.moviecataloguejetpackpro.data

data class Entity(
    var type: String,
    var id: String,
    var title: String,
    var overview: String,
    var tags: String,
    var score: String,
    var releaseDate: String,
    var imagePath: Int
)
