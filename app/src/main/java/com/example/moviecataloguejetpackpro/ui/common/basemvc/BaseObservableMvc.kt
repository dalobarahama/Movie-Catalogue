package com.example.moviecataloguejetpackpro.ui.common.basemvc

interface BaseObservableMvc<Listener> : BaseMvc {
    fun registerListener(listener: Listener)
    fun unregisterListener(listener: Listener)
}