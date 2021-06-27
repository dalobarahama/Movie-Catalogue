package com.example.moviecataloguejetpackpro.ui.tvShowsBookmark

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

class TvShowBookmarkAdapter :
    PagedListAdapter<TvShowEntityLocal, TvShowBookmarkAdapter.ViewHolder>(DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemMovieTvshowBinding =
            ItemMovieTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemMovieTvshowBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShows = getItem(position)
        if (tvShows != null) {
            holder.bind(tvShows)
        }
    }

    inner class ViewHolder(private val binding: ItemMovieTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntityLocal) {
            with(binding) {
                android.util.Log.i(
                    "TAG",
                    "onViewCreated: " + tvShow.name + "\n" + tvShow.overview
                )
                titleItemMovieTvshow.text = tvShow.name
                overviewItemMovieTvshow.text = tvShow.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context,
                        DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ENTITY,
                        tvShow.id)
                    intent.putExtra(DetailActivity.EXTRA_TYPE,
                        DetailActivity.EXTRA_TV_SHOW_TYPE)
                    itemView.context.startActivity(intent)
                }
            }
            Glide.with(itemView.context)
                .load(IMAGE_BASE_URL + tvShow.posterPath)
                .into(binding.posterItemMovieTvshow)
        }
    }
}