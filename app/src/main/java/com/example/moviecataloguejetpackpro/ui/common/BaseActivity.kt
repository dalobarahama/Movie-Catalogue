package com.example.moviecataloguejetpackpro.ui.common

import androidx.appcompat.app.AppCompatActivity
import com.example.moviecataloguejetpackpro.MyApp

open class BaseActivity : AppCompatActivity() {
    val appComposition get() = (application as MyApp).appModule
}