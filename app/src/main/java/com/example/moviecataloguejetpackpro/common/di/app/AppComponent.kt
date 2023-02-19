package com.example.moviecataloguejetpackpro.common.di.app

import com.example.moviecataloguejetpackpro.common.di.activity.ActivityComponent
import com.example.moviecataloguejetpackpro.common.di.activity.ActivityModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent

}