package com.example.moviecataloguejetpackpro.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object Utils {
    fun loadImage(context: Context, url: String?, imageView: ImageView) {
        Glide.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }

    fun loadImageWithPlaceholder(
        context: Context,
        imageUrl: String,
        imageView: ImageView,
        placeholderResId: Int,
    ) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(placeholderResId)
            .into(imageView)
    }
}