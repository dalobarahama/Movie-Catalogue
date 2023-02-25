package com.example.moviecataloguejetpackpro.ui.main

import android.os.Bundle
import com.example.moviecataloguejetpackpro.databinding.ActivityMainBinding
import com.example.moviecataloguejetpackpro.ui.common.BaseActivity
import com.example.moviecataloguejetpackpro.ui.common.BottomMenuNavigation
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var bottomMenuNavigation: BottomMenuNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)

        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.bnv.setOnItemSelectedListener(bottomMenuNavigation.navigation())

        bottomMenuNavigation.defaultMenu()
    }
}