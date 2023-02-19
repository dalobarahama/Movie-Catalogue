package com.example.moviecataloguejetpackpro.common.di.activity

import com.example.moviecataloguejetpackpro.common.di.presentation.PresentationComponent
import com.example.moviecataloguejetpackpro.common.di.presentation.PresentationModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}