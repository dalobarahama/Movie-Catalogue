package com.example.moviecataloguejetpackpro.ui.common.basemvc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

interface ViewMvc {
    fun getRootView(): View
    fun setRootView(layoutId: Int, layoutInflater: LayoutInflater, parent: ViewGroup)
    fun getContext(): Context
}