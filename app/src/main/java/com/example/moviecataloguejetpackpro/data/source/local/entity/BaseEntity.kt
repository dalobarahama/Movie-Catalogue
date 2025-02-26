package com.example.moviecataloguejetpackpro.data.source.local.entity

import android.os.Parcelable

interface BaseEntity : Parcelable {
    val idLocal: Int?
    val idRemote: Int
    val overview: String
    val posterPath: String
    val voteAverage: Double
    var bookmarked: Boolean
}
