package com.example.moviecataloguejetpackpro.data.source.remote.response

import android.os.Parcelable
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TVShowResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val tvShows: List<TVShowEntity>,

    @field:SerializedName("total_results")
    val totalResults: Int,
) : Parcelable

