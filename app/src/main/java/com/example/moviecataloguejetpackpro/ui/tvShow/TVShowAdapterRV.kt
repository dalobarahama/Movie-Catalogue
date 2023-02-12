package com.example.moviecataloguejetpackpro.ui.tvShow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.data.source.local.entity.TVShowEntity
import com.example.moviecataloguejetpackpro.databinding.ItemMovieTvshowBinding

class TVShowAdapterRV : RecyclerView.Adapter<TVShowAdapterRV.ListViewHolder>() {

    class ListViewHolder(private val binding: ItemMovieTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShowEntity: TVShowEntity) {
            binding.titleItemMovieTvshow.text = tvShowEntity.name
            binding.overviewItemMovieTvshow.text = tvShowEntity.overview
            Glide.with(itemView.context)
                .load(IMAGE_BASE_URL + tvShowEntity.posterPath)
                .into(binding.posterItemMovieTvshow)
        }
    }

    private var tvShowList: List<TVShowEntity> = emptyList()

    fun submitList(tvShowList: List<TVShowEntity>) {
        this.tvShowList = tvShowList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemMovieTvShowBinding =
            ItemMovieTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemMovieTvShowBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val tvShowEntity = tvShowList[position]

        holder.bind(tvShowEntity)
    }

    override fun getItemCount(): Int {
        return tvShowList.size
    }

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }
}