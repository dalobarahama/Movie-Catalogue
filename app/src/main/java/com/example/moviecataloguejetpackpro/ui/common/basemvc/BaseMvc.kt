package com.example.moviecataloguejetpackpro.ui.common.basemvc

import android.content.Context
import android.view.View

interface BaseMvc {
    fun getRootView(): View
    fun getContext(): Context
}