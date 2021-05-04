package com.example.moviecataloguejetpackpro.ui.tvShow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.data.TVShowEntity
import com.example.moviecataloguejetpackpro.databinding.ItemMovieTvshowBinding

class TVShowAdapter : RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder>() {
    private var listTVShows = ArrayList<TVShowEntity>()

    fun setMovies(tvShows: List<TVShowEntity>?) {
        if (tvShows == null) return
        this.listTVShows.clear()
        this.listTVShows.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val itemMovieTvshowBinding =
            ItemMovieTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowViewHolder(itemMovieTvshowBinding)
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        val tvShow = listTVShows[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTVShows.size

    class TVShowViewHolder(private val binding: ItemMovieTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TVShowEntity) {
            with(binding) {
                titleItemMovieTvshow.text = tvShow.title
                overviewItemMovieTvshow.text = tvShow.overview
            }
            Glide.with(itemView.context)
                .load(tvShow.imagePath)
                .into(binding.posterItemMovieTvshow)
        }
    }
}