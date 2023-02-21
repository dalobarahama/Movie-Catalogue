package com.example.moviecataloguejetpackpro.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntityLocal
import com.example.moviecataloguejetpackpro.data.source.local.entity.TvShowEntityLocal

@Database(
    entities = [MovieEntityLocal::class, TvShowEntityLocal::class],
    version = 1,
    exportSchema = false
)
abstract class MovieTvShowDatabase() : RoomDatabase() {
    abstract fun dao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: MovieTvShowDatabase? = null

        fun getInstance(context: Context): MovieTvShowDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieTvShowDatabase::class.java,
                    "MovieTvShowDatabase.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}