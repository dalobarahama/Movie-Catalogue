package com.example.moviecataloguejetpackpro.ui.moviesBookmark

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.data.source.local.entity.MovieEntityLocal
import com.example.moviecataloguejetpackpro.databinding.ItemMovieTvshowBinding
import com.example.moviecataloguejetpackpro.ui.detail.DetailActivity

class MovieBookmarkAdapter : RecyclerView.Adapter<MovieBookmarkAdapter.ViewHolder>() {
    private val listBookmarkedMovies = ArrayList<MovieEntityLocal>()

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    fun setBookmarkedMovies(movies: List<MovieEntityLocal>) {
        this.listBookmarkedMovies.clear()
        this.listBookmarkedMovies.addAll(movies)

        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemMovieTvshowBinding =
            ItemMovieTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemMovieTvshowBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies = listBookmarkedMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listBookmarkedMovies.size

    inner class ViewHolder(private val binding: ItemMovieTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntityLocal) {
            with(binding) {
                android.util.Log.i(
                    "TAG",
                    "onViewCreated: " + movie.title + "\n" + movie.overview
                )
                titleItemMovieTvshow.text = movie.title
                overviewItemMovieTvshow.text = movie.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context,
                        DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ENTITY,
                        movie.id)
                    intent.putExtra(DetailActivity.EXTRA_TYPE,
                        DetailActivity.EXTRA_MOVIE_TYPE)
                    itemView.context.startActivity(intent)
                }
            }
            Glide.with(itemView.context)
                .load(IMAGE_BASE_URL + movie.posterPath)
                .into(binding.posterItemMovieTvshow)
        }
    }
}