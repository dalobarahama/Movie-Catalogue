package com.example.moviecataloguejetpackpro.ui.common

import androidx.fragment.app.Fragment
import com.example.moviecataloguejetpackpro.common.di.presentation.PresentationModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class BaseFragment : Fragment() {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(
            PresentationModule()
        )
    }

    val injector get() = presentationComponent

    val coroutineScope get() = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
}