package com.example.moviecataloguejetpackpro.ui.common

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecataloguejetpackpro.MyApp
import com.example.moviecataloguejetpackpro.common.di.activity.ActivityModule
import com.example.moviecataloguejetpackpro.common.di.presentation.PresentationModule
import com.example.moviecataloguejetpackpro.utils.Utils

open class BaseActivity : AppCompatActivity() {
    val activityComponent by lazy {
        (application as MyApp).appComponent.newActivityComponent(ActivityModule(this))
    }

    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent(PresentationModule())
    }

    fun loadImage(imageUrl: String, imageView: ImageView) {
        Utils.loadImage(this, imageUrl, imageView)
    }

    val injector get() = presentationComponent
}