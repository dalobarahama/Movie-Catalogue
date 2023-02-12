package com.example.moviecataloguejetpackpro.ui.common

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    val appComposition get() = (requireActivity() as BaseActivity).appComposition
}