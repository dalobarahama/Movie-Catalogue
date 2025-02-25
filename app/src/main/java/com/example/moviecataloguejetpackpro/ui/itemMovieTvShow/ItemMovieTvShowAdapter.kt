package com.example.moviecataloguejetpackpro.ui.itemMovieTvShow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemMovieTvShowAdapter<T : Any>(
    private val layoutInflater: LayoutInflater,
    private val listener: Listener<T>,
) : RecyclerView.Adapter<ItemMovieTvShowAdapter.ListViewHolder<T>>(),
    ItemMovieTvShowMvc.Listener<T> {

    interface Listener<T> {
        fun onItemOnClicked(entity: T)
    }

    class ListViewHolder<T : Any>(private val viewMvc: ItemMovieTvShowMvc<T>) :
        RecyclerView.ViewHolder(viewMvc.getRootView()) {
        fun bind(entity: T) {
            viewMvc.bindData(entity)
        }
    }

    private var movieList: List<T> = emptyList()

    fun submitList(movieList: List<T>) {
        this.movieList = movieList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder<T> {
        val viewMvc = ItemMovieTvShowMvcImpl<T>(layoutInflater, parent)
        viewMvc.registerListener(this)
        return ListViewHolder(viewMvc)
    }

    override fun onBindViewHolder(holder: ListViewHolder<T>, position: Int) {
        val entity = movieList[position]
        holder.bind(entity)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onItemClicked(entity: T) {
        listener.onItemOnClicked(entity)
    }
}