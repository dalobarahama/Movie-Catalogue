package com.example.moviecataloguejetpackpro

import android.app.Application
import com.example.moviecataloguejetpackpro.common.composition.app.AppModule

class MyApp : Application() {

    lateinit var appModule: AppModule

    override fun onCreate() {
        appModule = AppModule(this)
        super.onCreate()
    }

}