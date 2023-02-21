package com.example.moviecataloguejetpackpro.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.databinding.ItemMovieTvshowBinding

class MovieAdapterRV : RecyclerView.Adapter<MovieAdapterRV.ListViewHolder>() {

    class ListViewHolder(private val binding: ItemMovieTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieEntity: MovieEntity) {
            binding.titleItemMovieTvshow.text = movieEntity.title
            binding.overviewItemMovieTvshow.text = movieEntity.overview
            Glide.with(itemView.context)
                .load(IMAGE_BASE_URL + movieEntity.posterPath)
                .into(binding.posterItemMovieTvshow)
        }
    }

    private var movieList: List<MovieEntity> = emptyList()

    fun submitList(movieList: List<MovieEntity>) {
        this.movieList = movieList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemMovieTvShowBinding =
            ItemMovieTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemMovieTvShowBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movieEntity = movieList[position]

        holder.bind(movieEntity)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }
}