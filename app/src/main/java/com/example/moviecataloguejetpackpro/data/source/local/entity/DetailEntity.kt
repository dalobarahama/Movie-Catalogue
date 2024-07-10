package com.example.moviecataloguejetpackpro.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailEntity(
    val title: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: String,
    val posterPath: String,
) : Parcelable