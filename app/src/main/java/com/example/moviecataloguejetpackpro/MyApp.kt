package com.example.moviecataloguejetpackpro

import android.app.Application
import com.example.moviecataloguejetpackpro.common.di.app.AppComponent
import com.example.moviecataloguejetpackpro.common.di.app.AppModule
import com.example.moviecataloguejetpackpro.common.di.app.DaggerAppComponent

class MyApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }

}