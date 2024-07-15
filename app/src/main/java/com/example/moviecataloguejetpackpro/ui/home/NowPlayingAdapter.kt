package com.example.moviecataloguejetpackpro.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.data.source.local.entity.DetailEntity
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntity
import com.example.moviecataloguejetpackpro.databinding.ItemNewMoviesBinding
import com.example.moviecataloguejetpackpro.ui.detail.DetailActivity
import com.example.moviecataloguejetpackpro.utils.Utils

class NowPlayingAdapter : RecyclerView.Adapter<NowPlayingAdapter.HomeViewHolder>() {
    class HomeViewHolder(private val binding: ItemNewMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieEntity: MovieEntity) {
            binding.tvItemNewMoviesTitle.text = movieEntity.title

            Utils.loadImageWithPlaceholder(
                itemView.context,
                IMAGE_BASE_URL + movieEntity.posterPath,
                binding.ivItemNewMovies,
                R.drawable.placeholder_image
            )

            binding.root.setOnClickListener {

                val detailEntity = DetailEntity(
                    movieEntity.originalTitle,
                    movieEntity.overview,
                    movieEntity.releaseDate,
                    movieEntity.voteAverage.toString(),
                    movieEntity.posterPath
                )

                val intent = Intent(binding.root.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ENTITY, detailEntity)
                binding.root.context.startActivity(intent)
            }
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
        val movieEntity = movieList[position]

        holder.bind(movieEntity)
    }

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }
}