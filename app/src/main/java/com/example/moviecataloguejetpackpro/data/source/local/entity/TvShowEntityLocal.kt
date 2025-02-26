package com.example.moviecataloguejetpackpro.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show_entities")
data class TvShowEntityLocal(
    @PrimaryKey
    @ColumnInfo(name = "id_local")
    val idLocal: Int,

    @ColumnInfo(name = "id_remote")
    val idRemote: Int,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false,
)
