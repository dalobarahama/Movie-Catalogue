package com.example.moviecataloguejetpackpro.ui.movie

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntityLocal
import com.example.moviecataloguejetpackpro.databinding.ItemMovieTvshowBinding
import com.example.moviecataloguejetpackpro.ui.detail.DetailActivity

class MovieAdapter :
    PagedListAdapter<MovieEntityLocal, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntityLocal>() {
            override fun areItemsTheSame(
                oldItem: MovieEntityLocal,
                newItem: MovieEntityLocal,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieEntityLocal,
                newItem: MovieEntityLocal,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieTvshowBinding =
            ItemMovieTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieTvshowBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    class MovieViewHolder(private val binding: ItemMovieTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntityLocal) {
            with(binding) {
                Log.i(
                    "TAG",
                    "onViewCreated: " + movie.title + "\n" + movie.overview
                )
                titleItemMovieTvshow.text = movie.title
                overviewItemMovieTvshow.text = movie.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ENTITY, movie.id)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.EXTRA_MOVIE_TYPE)
                    itemView.context.startActivity(intent)
                }
            }
            Glide.with(itemView.context)
                .load(IMAGE_BASE_URL + movie.posterPath)
                .into(binding.posterItemMovieTvshow)
        }
    }
}