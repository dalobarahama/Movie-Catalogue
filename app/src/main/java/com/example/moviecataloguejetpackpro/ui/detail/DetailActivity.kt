package com.example.moviecataloguejetpackpro.ui.detail

import android.os.Bundle
import android.view.Menu
import androidx.core.content.ContextCompat
import com.example.moviecataloguejetpackpro.R
import com.example.moviecataloguejetpackpro.data.source.local.entity.DetailEntity
import com.example.moviecataloguejetpackpro.databinding.ActivityDetailBinding
import com.example.moviecataloguejetpackpro.ui.common.BaseActivity

class DetailActivity : BaseActivity(), DetailActivityMvcImpl.Listener {

    companion object {
        const val EXTRA_ENTITY = "extra_entity"
    }

    private lateinit var viewMvc: DetailActivityMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        viewMvc = DetailActivityMvcImpl(layoutInflater, null)
        setContentView(viewMvc.getRootView())

        setSupportActionBar(viewMvc.getToolbar())

        val detailEntity = intent?.getParcelableExtra<DetailEntity>(EXTRA_ENTITY)
        if (detailEntity != null) {
            viewMvc.populateTrendingEntity(detailEntity)
        }
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
    }

    override fun onStop() {
        viewMvc.unregisterListener(this)
        super.onStop()
    }

    override fun onUpClicked() {
        onBackPressed()
    }
}