package com.example.moviecataloguejetpackpro.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.data.Entity
import com.example.moviecataloguejetpackpro.databinding.ItemMovieTvshowBinding
import com.example.moviecataloguejetpackpro.ui.detail.DetailActivity

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<Entity>()

    fun setMovies(movies: List<Entity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieTvshowBinding =
            ItemMovieTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieTvshowBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    class MovieViewHolder(private val binding: ItemMovieTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Entity) {
            with(binding) {
                titleItemMovieTvshow.text = movie.title
                overviewItemMovieTvshow.text = movie.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, movie.type)
                    intent.putExtra(DetailActivity.EXTRA_ID, movie.id)
                    intent.putExtra(DetailActivity.EXTRA_TITLE, movie.title)
                    intent.putExtra(DetailActivity.EXTRA_OVERVIEW, movie.overview)
                    intent.putExtra(DetailActivity.EXTRA_TAGS, movie.tags)
                    intent.putExtra(DetailActivity.EXTRA_RELEASE_DATE, movie.releaseDate)
                    intent.putExtra(DetailActivity.EXTRA_SCORE, movie.score)
                    intent.putExtra(DetailActivity.EXTRA_IMAGE_PATH, movie.imagePath)
                    itemView.context.startActivity(intent)
                }
            }
            Glide.with(itemView.context)
                .load(movie.imagePath)
                .into(binding.posterItemMovieTvshow)
        }
    }
}