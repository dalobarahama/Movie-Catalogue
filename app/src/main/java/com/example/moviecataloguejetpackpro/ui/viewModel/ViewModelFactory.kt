package com.example.moviecataloguejetpackpro.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecataloguejetpackpro.data.Repository
import com.example.moviecataloguejetpackpro.di.Injection
import com.example.moviecataloguejetpackpro.ui.detail.DetailViewModel
import com.example.moviecataloguejetpackpro.ui.movie.MovieViewModel
import com.example.moviecataloguejetpackpro.ui.tvShow.TVShowViewModel

class ViewModelFactory private constructor(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(repository) as T
            }
            modelClass.isAssignableFrom(TVShowViewModel::class.java) -> {
                TVShowViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}