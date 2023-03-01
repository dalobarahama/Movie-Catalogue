package com.example.moviecataloguejetpackpro.data.source.remote.response

import com.example.moviecataloguejetpackpro.data.source.local.entity.TrendingEntity
import com.google.gson.annotations.SerializedName

data class TrendingResponse(
    val page: Long,
    val results: List<TrendingEntity>,

    @field:SerializedName("total_pages")
    val totalPages: Long,

    @field:SerializedName("total_results")
    val totalResults: Long,
)