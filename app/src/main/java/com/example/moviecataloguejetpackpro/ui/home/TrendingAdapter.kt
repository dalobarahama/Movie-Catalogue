package com.example.moviecataloguejetpackpro.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.data.source.local.entity.TrendingEntity
import com.example.moviecataloguejetpackpro.databinding.ItemMovieTrendingBinding
import com.example.moviecataloguejetpackpro.ui.detail.DetailActivity
import com.example.moviecataloguejetpackpro.utils.Utils

class TrendingAdapter : RecyclerView.Adapter<TrendingAdapter.HomeViewHolder>() {
    class HomeViewHolder(private val binding: ItemMovieTrendingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(trendingEntity: TrendingEntity) {
            Utils.loadImageWithPlaceholder(
                itemView.context,
                IMAGE_BASE_URL + trendingEntity.posterPath,
                binding.ivItemMovie,
                R.drawable.placeholder_image
            )

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ENTITY, trendingEntity)
                binding.root.context.startActivity(intent)
            }
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