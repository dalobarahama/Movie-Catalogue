package com.example.moviecataloguejetpackpro.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.databinding.ItemNewMoviesBinding

class NowPlayingAdapter : RecyclerView.Adapter<NowPlayingAdapter.HomeViewHolder>() {
    class HomeViewHolder(private val binding: ItemNewMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieEntity: MovieEntity) {
            binding.tvItemNewMoviesTitle.text = movieEntity.title

            Glide.with(itemView.context)
                .load(IMAGE_BASE_URL + movieEntity.posterPath)
                .into(binding.ivItemNewMovies)
        }
    }

    private var movieList: List<MovieEntity> = emptyList()

    fun submitList(movieList: List<MovieEntity>) {
        this.movieList = movieList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemNewMoviesBinding =
            ItemNewMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(itemNewMoviesBinding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val movieList = movieList[position]

        holder.bind(movieList)
    }

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }
}