package com.example.moviecataloguejetpackpro.data.source.local.entity

import com.google.gson.annotations.SerializedName

data class MovieEntity(
    var type: String,

    @field:SerializedName("id")
    var id: String,

    @field:SerializedName("original_title")
    var title: String,

    @field:SerializedName("overview")
    var overview: String,

    @field:SerializedName("id")
    var tags: String,

    @field:SerializedName("vote_average")
    var score: String,

    @field:SerializedName("release_date")
    var releaseDate: String,

    @field:SerializedName("poster_path")
    var imagePath: Int
)
