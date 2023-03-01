package com.example.moviecataloguejetpackpro.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.data.source.local.entity.TrendingEntity
import com.example.moviecataloguejetpackpro.databinding.ItemMovieTrendingBinding

class TrendingAdapter : RecyclerView.Adapter<TrendingAdapter.HomeViewHolder>() {
    class HomeViewHolder(private val binding: ItemMovieTrendingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(trendingEntity: TrendingEntity) {
            Glide.with(itemView.context)
                .load(IMAGE_BASE_URL + trendingEntity.posterPath)
                .into(binding.ivItemMovie)
        }
    }

    private var trendingList: List<TrendingEntity> = emptyList()

    fun submitList(movieList: List<TrendingEntity>) {
        this.trendingList = movieList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemNewMoviesBinding =
            ItemMovieTrendingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(itemNewMoviesBinding)
    }

    override fun getItemCount(): Int {
        return trendingList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val trendingEntity = trendingList[position]

        holder.bind(trendingEntity)
    }

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }
}