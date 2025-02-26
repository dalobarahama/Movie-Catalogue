package com.example.moviecataloguejetpackpro.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entities")
data class MovieEntityLocal(
    @PrimaryKey
    @ColumnInfo(name = "id_local")
    val idLocal: Int,

    @ColumnInfo(name = "id_remote")
    val idRemote: Int,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false,

    )
