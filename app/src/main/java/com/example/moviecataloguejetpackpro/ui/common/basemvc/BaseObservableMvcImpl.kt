package com.example.moviecataloguejetpackpro.ui.common.basemvc

import android.view.View

abstract class BaseObservableMvcImpl<Listener> : BaseObservableMvc<Listener> {

    private val listeners = HashSet<Listener>()

    override fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    fun getListener(): Listener? {
        var listener: Listener? = null
        for (listener1 in listeners) {
            listener = listener1
        }
        return listener
    }

    fun <T : View> findViewById(id: Int): T {
        return getRootView().findViewById(id)
    }
}