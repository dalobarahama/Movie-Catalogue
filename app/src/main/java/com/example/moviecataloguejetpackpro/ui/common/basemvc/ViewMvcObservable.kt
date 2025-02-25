package com.example.moviecataloguejetpackpro.ui.common.basemvc

interface ViewMvcObservable<Listener> : ViewMvc {
    fun registerListener(listener: Listener)
    fun unregisterListener(listener: Listener)
}