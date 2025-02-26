package com.example.moviecataloguejetpackpro.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movie_entities")
@Parcelize
data class MovieEntityLocal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_local")
    override val idLocal: Int? = null,

    @ColumnInfo(name = "id_remote")
    override val idRemote: Int,

    @ColumnInfo(name = "overview")
    override val overview: String,

    @ColumnInfo(name = "poster_path")
    override val posterPath: String,

    @ColumnInfo(name = "vote_average")
    override val voteAverage: Double,

    @ColumnInfo(name = "bookmarked")
    override var bookmarked: Boolean = false,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,
): BaseEntity, Parcelable
