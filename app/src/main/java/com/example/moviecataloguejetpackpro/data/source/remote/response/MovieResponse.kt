package com.example.moviecataloguejetpackpro.data.source.remote.response

import android.os.Parcelable
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val movies: List<MovieEntity>,

    @field:SerializedName("total_results")
    val totalResults: Int,
) : Parcelable
