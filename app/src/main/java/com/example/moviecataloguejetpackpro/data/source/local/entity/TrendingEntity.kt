package com.example.moviecataloguejetpackpro.data.source.local.entity

import com.google.gson.annotations.SerializedName

data class TrendingEntity(
    val adult: Boolean,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    val id: Long,
    val name: String? = null,

    @field:SerializedName("original_language")
    val originalLanguage: String,

    @field:SerializedName("original_name")
    val originalName: String? = null,

    val overview: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("media_type")
    val mediaType: String,

    @field:SerializedName("genre_ids")
    val genreIDS: List<Long>,

    val popularity: Double,

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("vote_count")
    val voteCount: Long,

    @field:SerializedName("origin_country")
    val originCountry: List<String>? = null,

    val title: String? = null,

    @field:SerializedName("original_title")
    val originalTitle: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    val video: Boolean? = null,
)