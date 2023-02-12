package com.example.moviecataloguejetpackpro

import android.app.Application
import com.example.moviecataloguejetpackpro.common.composition.AppComposition

class MyApp : Application() {

    lateinit var appComposition: AppComposition

    override fun onCreate() {
        appComposition = AppComposition(this)
        super.onCreate()
    }

}