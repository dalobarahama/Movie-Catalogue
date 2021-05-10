package com.example.moviecataloguejetpackpro.ui.tvShow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecataloguejetpackpro.data.Entity
import com.example.moviecataloguejetpackpro.databinding.ItemMovieTvshowBinding
import com.example.moviecataloguejetpackpro.ui.detail.DetailActivity

class TVShowAdapter : RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder>() {
    private var listTVShows = ArrayList<Entity>()

    fun setMovies(tvShows: List<Entity>?) {
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
        fun bind(tvShow: Entity) {
            with(binding) {
                titleItemMovieTvshow.text = tvShow.title
                overviewItemMovieTvshow.text = tvShow.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, tvShow.type)
                    intent.putExtra(DetailActivity.EXTRA_ID, tvShow.id)
                    intent.putExtra(DetailActivity.EXTRA_TITLE, tvShow.title)
                    intent.putExtra(DetailActivity.EXTRA_OVERVIEW, tvShow.overview)
                    intent.putExtra(DetailActivity.EXTRA_TAGS, tvShow.tags)
                    intent.putExtra(DetailActivity.EXTRA_RELEASE_DATE, tvShow.releaseDate)
                    intent.putExtra(DetailActivity.EXTRA_SCORE, tvShow.score)
                    intent.putExtra(DetailActivity.EXTRA_IMAGE_PATH, tvShow.imagePath)
                    itemView.context.startActivity(intent)
                }
            }
            Glide.with(itemView.context)
                .load(tvShow.imagePath)
                .into(binding.posterItemMovieTvshow)
        }
    }
}