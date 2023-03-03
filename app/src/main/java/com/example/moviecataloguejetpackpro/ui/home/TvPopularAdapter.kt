package com.example.moviecataloguejetpackpro.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.databinding.ItemNewMoviesBinding

class TvPopularAdapter : RecyclerView.Adapter<TvPopularAdapter.HomeViewHolder>() {
    class HomeViewHolder(private val binding: ItemNewMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShowEntity: TVShowEntity) {
            binding.tvItemNewMoviesTitle.text = tvShowEntity.name

            Glide.with(itemView.context)
                .load(IMAGE_BASE_URL + tvShowEntity.posterPath)
                .into(binding.ivItemNewMovies)
        }
    }

    private var tvShowList: List<TVShowEntity> = emptyList()

    fun submitList(tvShowList: List<TVShowEntity>) {
        this.tvShowList = tvShowList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemNewMoviesBinding =
            ItemNewMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(itemNewMoviesBinding)
    }

    override fun getItemCount(): Int {
        return tvShowList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val tvShowEntity = tvShowList[position]

        holder.bind(tvShowEntity)
    }

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }
}