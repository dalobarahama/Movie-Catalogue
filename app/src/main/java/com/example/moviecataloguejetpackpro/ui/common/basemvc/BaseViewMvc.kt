package com.example.moviecataloguejetpackpro.ui.common.basemvc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseViewMvc : ViewMvc {

    private lateinit var rootView: View

    override fun getRootView(): View {
        return rootView
    }

    override fun setRootView(layoutId: Int, layoutInflater: LayoutInflater, parent: ViewGroup?) {
        rootView = layoutInflater.inflate(layoutId, parent, false)
    }

    override fun getContext(): Context {
        return getRootView().context
    }

    fun <T : View> findViewById(id: Int): T {
        return getRootView().findViewById(id)
    }
}