package com.example.moviecataloguejetpackpro.ui.tvShow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.data.source.local.entity.TvShowEntityLocal
import com.example.moviecataloguejetpackpro.databinding.ItemMovieTvshowBinding
import com.example.moviecataloguejetpackpro.ui.detail.DetailActivity

class TVShowAdapter :
    PagedListAdapter<TvShowEntityLocal, TVShowAdapter.TVShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntityLocal>() {
            override fun areItemsTheSame(
                oldItem: TvShowEntityLocal,
                newItem: TvShowEntityLocal,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TvShowEntityLocal,
                newItem: TvShowEntityLocal,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val itemMovieTvShowBinding =
            ItemMovieTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowViewHolder(itemMovieTvShowBinding)
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    class TVShowViewHolder(private val binding: ItemMovieTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntityLocal) {
            with(binding) {
                titleItemMovieTvshow.text = tvShow.name
                overviewItemMovieTvshow.text = tvShow.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ENTITY, tvShow.id)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.EXTRA_TV_SHOW_TYPE)
                    itemView.context.startActivity(intent)
                }
            }
            Glide.with(itemView.context)
                .load(IMAGE_BASE_URL + tvShow.posterPath)
                .into(binding.posterItemMovieTvshow)
        }
    }
}