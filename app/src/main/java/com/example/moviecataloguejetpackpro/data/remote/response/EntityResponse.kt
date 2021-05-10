package com.example.moviecataloguejetpackpro.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EntityResponse(
    var type: String,
    var id: String,
    var title: String,
    var overview: String,
    var tags: String,
    var score: String,
    var releaseDate: String,
    var imagePath: Int
) : Parcelable
