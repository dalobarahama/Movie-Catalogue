package com.example.moviecataloguejetpackpro.ui.common

import androidx.appcompat.app.AppCompatActivity
import com.example.moviecataloguejetpackpro.MyApp
import com.example.moviecataloguejetpackpro.common.di.activity.ActivityModule
import com.example.moviecataloguejetpackpro.common.di.presentation.PresentationModule

open class BaseActivity : AppCompatActivity() {
    val activityComponent by lazy {
        (application as MyApp).appComponent.newActivityComponent(ActivityModule(this))
    }

    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent(PresentationModule())
    }

    val injector get() = presentationComponent
}