package com.example.moviecataloguejetpackpro.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.ui.itemMovieTvShow.ItemMovieTvShowMvc
import com.example.moviecataloguejetpackpro.ui.itemMovieTvShow.ItemMovieTvShowMvcImpl

class MovieAdapterRV(private val layoutInflater: LayoutInflater, private val listener: Listener) :
    RecyclerView.Adapter<MovieAdapterRV.ListViewHolder>(), ItemMovieTvShowMvc.Listener {

    interface Listener {
        fun onItemOnClicked(movieEntity: MovieEntity)
    }

    class ListViewHolder(private val viewMvc: ItemMovieTvShowMvc) :
        RecyclerView.ViewHolder(viewMvc.getRootView()) {
        fun bind(movieEntity: MovieEntity) {
            viewMvc.bindData(movieEntity)
        }
    }

    private var movieList: List<MovieEntity> = emptyList()

    fun submitList(movieList: List<MovieEntity>) {
        this.movieList = movieList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val viewMvc = ItemMovieTvShowMvcImpl(layoutInflater, parent)
        viewMvc.registerListener(this)
        return ListViewHolder(viewMvc)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movieEntity = movieList[position]
        holder.bind(movieEntity)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onItemClicked(movieEntity: MovieEntity) {
        listener.onItemOnClicked(movieEntity)
    }
}